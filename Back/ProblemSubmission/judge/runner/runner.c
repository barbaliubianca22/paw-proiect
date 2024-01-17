#include <stdio.h>
#include <stdlib.h>
#include <sys/resource.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/resource.h>
#include <unistd.h>
#include <pthread.h>
#include <pwd.h>
#include <time.h>
#include <string.h>
#include <sys/times.h>
#include <fcntl.h>

// Exit for when using posix thread api
void exit_child_thread()
{
    exit(0);
}

int main(int argc, char **argv)
{

    int process_limit = 1;
    if (strcmp(argv[1], "java"))
        process_limit = 30;

    struct tms start, end;
    times(&start);

    int redirect_pipe[2];
    pipe(redirect_pipe);
    //stdin to pipe_in
    dup2(0, redirect_pipe[0]);

    pid_t pid = fork();
    if (pid == 0)
    {
        int err;

        // file where child output will be printed
        int fd_write = open("./runner.out", O_RDWR | O_CREAT, 0777);

        // Searching for the dummy user
        struct passwd *p;
        if ((p = getpwnam("dummy")) == NULL)
        {
            perror("dummy");
            return EXIT_FAILURE;
        }

        // use the dummy user
        // need sudo or capability
        //err = setuid(p->pw_uid);
        if (err < 0)
        {
            fprintf(stderr, "error: Failed to setuid of dummy user\n");
            return -1;
        }
        struct rlimit process_limit_set;
        process_limit_set.rlim_cur = process_limit;
        process_limit_set.rlim_max = process_limit;
        err = setrlimit(RLIMIT_NPROC, &process_limit_set);
        //         process_limit_set.rlim_cur = 100;
        // process_limit_set.rlim_max = 100;
        // err = setrlimit(RLIMIT_AS, &process_limit_set);

        if (err < 0)
        {
            fprintf(stderr, "error: Failed to setrlimit\n");
            return -1;
        }
        // setting the callback
        pthread_atfork(NULL, NULL, exit_child_thread);

        //take the parent stdin
        dup2(redirect_pipe[0], 0);
        err = dup2(fd_write, 1); // make child's stdout go to file
        if (err < 0)
        {
            fprintf(stderr, "error: Failed to redirect output\n");
            return -1;
        }

        err = execvp(*(argv + 1), argv + 1);
        if (err < 0)
        {
            fprintf(stderr, "error: Failed to start child process\n");
            return -1;
        }
    }

    int status;
    waitpid(pid, &status, 0);

    // child exited
    if (WIFEXITED(status))
    {
        times(&end);

        long clktck = sysconf(_SC_CLK_TCK);
        long cpu_time_used =
            ((end.tms_utime - start.tms_utime) +
             (end.tms_stime - start.tms_stime) +
             (end.tms_cutime - start.tms_cutime) +
             (end.tms_cstime - start.tms_cstime)) /
            (double)clktck * 1000;

        struct rusage res;
        getrusage(RUSAGE_CHILDREN, &res);
        // printf("\nMetrics: %ld %ld %ld\n", res.ru_maxrss, res.ru_utime.tv_sec * 1000 + res.ru_utime.tv_usec / 1000, res.ru_stime.tv_sec * 1000 + res.ru_stime.tv_usec / 1000);
        printf("%ld, %ld\n", res.ru_maxrss, cpu_time_used); //KB and milisec
    }
    return 0;
}