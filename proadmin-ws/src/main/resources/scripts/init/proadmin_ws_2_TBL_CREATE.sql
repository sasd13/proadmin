CREATE TABLE projects (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (code)
);

CREATE TABLE teachers (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (code)
);

CREATE TABLE students (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (code)
);

CREATE TABLE teams (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (code)
);

CREATE TABLE studentteams (
	_id SERIAL,
	student_code VARCHAR(50) NOT NULL,
	team_code VARCHAR(50) NOT NULL,
	PRIMARY KEY (student_code, team_code),
	FOREIGN KEY (student_code) REFERENCES students (code),
	FOREIGN KEY (team_code) REFERENCES teams (code)
);

CREATE TABLE runnings (
	_id SERIAL,
	year INT NOT NULL,
	project_code VARCHAR(50) NOT NULL,
	teacher_code VARCHAR(50) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (project_code, teacher_code),
	FOREIGN KEY (project_code) REFERENCES projects (code),
	FOREIGN KEY (teacher_code) REFERENCES teachers (code)
);

CREATE TABLE academiclevels (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	PRIMARY KEY (code)
);

CREATE TABLE runningteams (
	_id SERIAL,
	running_project_code VARCHAR(50) NOT NULL,
	running_teacher_code VARCHAR(50) NOT NULL,
	team_code VARCHAR(50) NOT NULL,
	academiclevel_code VARCHAR(50) NOT NULL,
	PRIMARY KEY (running_project_code, running_teacher_code, team_code, academiclevel_code),
	FOREIGN KEY (running_project_code) REFERENCES runnings (project_code),
	FOREIGN KEY (running_teacher_code) REFERENCES runnings (project_code),
	FOREIGN KEY (team_code) REFERENCES teams (code),
	FOREIGN KEY (academiclevel_code) REFERENCES academiclevels (code)
);

CREATE TABLE reports (
	_id SERIAL,
	code VARCHAR(50) NOT NULL,
	meetingdate VARCHAR(255) NOT NULL,
	session INT NOT NULL,
	comment TEXT,
	runningteam_running_project_code VARCHAR(50) NOT NULL,
	runningteam_running_teacher_code VARCHAR(50) NOT NULL,
	runningteam_team_code VARCHAR(50) NOT NULL,
	runningteam_academiclevel_code VARCHAR(50) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (code),
	FOREIGN KEY (runningteam_running_project_code) REFERENCES runningteams (running_project_code),
	FOREIGN KEY (runningteam_running_teacher_code) REFERENCES runningteams (running_teacher_code),
	FOREIGN KEY (runningteam_team_code) REFERENCES runningteams (team_code),
	FOREIGN KEY (runningteam_academiclevel_code) REFERENCES runningteams (academiclevel_code)
);

CREATE TABLE leadevaluations (
	_id SERIAL,
	planningmark DOUBLE NOT NULL,
	planningcomment TEXT,
	communicationmark DOUBLE NOT NULL,
	communicationcomment TEXT,
	report_code VARCHAR(50) NOT NULL,
	student_code VARCHAR(50) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (report_code, student_code),
	FOREIGN KEY (report_code) REFERENCES reports (code),
	FOREIGN KEY (student_code) REFERENCES students (code)
);

CREATE TABLE individualevaluations (
	_id SERIAL,
	mark DOUBLE NOT NULL,
	report_code VARCHAR(50) NOT NULL,
	student_code VARCHAR(50) NOT NULL,
	_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (report_code, student_code),
	FOREIGN KEY (report_code) REFERENCES reports (code),
	FOREIGN KEY (student_code) REFERENCES students (code)
);

COMMIT;