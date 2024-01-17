Use codeWiz;

Create Table Submission(
	submissionID int primary key AUTO_INCREMENT,
	userID int,
	problemID int,
	submissionText text(32768),
	usedLanguage varchar(16),
	time datetime,
	status ENUM('Waiting', 'Compile_Error', 'Compile_Time_Exceeded', 'Processed')
);

Create Table TestResult(
	submissionID int,
	testID int,
	evaluationTimeMsec int,
	memUsedKbytes int,
	status ENUM('Succeded', 'Memory_Overflow', 'Run_Time_Exceeded', 'Non_Matching_Output'),
	Primary key (submissionID, testID),
	Constraint fk_submissionID foreign key (submissionID) references Submission(submissionID)
);


