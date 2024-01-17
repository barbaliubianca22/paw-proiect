package com.example.gatewayservice.config;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private static final List<String> EXCLUDED_PATHS = Arrays.asList("/auth/login", "/auth/register");

    public AuthFilter() {
        super(Config.class);

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            String path = exchange.getRequest().getURI().getPath();

            if (EXCLUDED_PATHS.contains(path)) {
                return chain.filter(exchange);
            }
            boolean isValidToken = validateToken(token);
            if (isValidToken) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };

    }
    private boolean validateToken(String token) {
        String curlCommand = String.format("curl --location 'http://auth-serv:8080/auth/validate' " +
                "--header 'Content-Type: application/json' " +
                "--data '{\"token\":\"%1$s\"}'", token);
        System.out.println(curlCommand);
        try {
            Process process = new ProcessBuilder().command("/bin/sh", "-c", curlCommand).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
//            new Thread(() -> {
//                try {
//                    new ProcessBuilder().command("bash", "-c", curlCommand).start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
            StringBuilder response = new StringBuilder();
            var start = System.currentTimeMillis();

            while ((line = reader.readLine()) != null && start + 5000 > System.currentTimeMillis()) {
                response.append(line);
                System.out.println(line);
                if (line.contains(token))
                    return true;
            }
            process.destroy();
            int exitCode;
            try {
                exitCode = process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException("Error waiting for the process to complete.", e);
            }

            if (exitCode == 0) {
                System.out.println("Response from server: " + response.toString());
            } else {
                System.err.println("Error executing cURL command. Exit code: " + exitCode);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error executing cURL command.", e);
        }
        return false;
    }

    public static class Config {
    }
}