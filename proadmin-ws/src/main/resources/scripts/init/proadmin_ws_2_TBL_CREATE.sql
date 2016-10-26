CREATE TABLE teachers (
	id SERIAL,
	number VARCHAR(255) NOT NULL UNIQUE,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id)
);

CREATE TABLE projects (
	id SERIAL,
	academiclevel VARCHAR(255) NOT NULL,
	code VARCHAR(255) NOT NULL UNIQUE,
	title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id)
);

CREATE TABLE students (
	id SERIAL,
	number VARCHAR(255) NOT NULL UNIQUE,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	academiclevel VARCHAR(255) NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id)
);

CREATE TABLE teams (
	id SERIAL,
	code VARCHAR(255) NOT NULL UNIQUE,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id)
);

CREATE TABLE studentteams (
	id SERIAL,
	student_id BIGINT UNSIGNED NOT NULL,
	team_id BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (student_id) REFERENCES students (id),
	FOREIGN KEY (team_id) REFERENCES teams (id),
	CONSTRAINT UNIQUE_CONSTRAINT UNIQUE (student_id, team_id)
);

CREATE TABLE runnings (
	id SERIAL,
	year INT NOT NULL,
	teacher_id BIGINT UNSIGNED NOT NULL,
	project_id BIGINT UNSIGNED NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (teacher_id) REFERENCES teachers (id),
	FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE runningteams (
	id SERIAL,
	running_id BIGINT UNSIGNED NOT NULL,
	team_id BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (running_id) REFERENCES runnings (id),
	FOREIGN KEY (team_id) REFERENCES teams (id),
	CONSTRAINT UNIQUE_CONSTRAINT UNIQUE (running_id, team_id)
);

CREATE TABLE reports (
	id SERIAL,
	number VARCHAR(255) NOT NULL UNIQUE,
	meetingdate VARCHAR(255) NOT NULL,
	session INT NOT NULL,
	comment TEXT,
	runningteam_id BIGINT UNSIGNED NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (runningteam_id) REFERENCES runningteams (id)
);

CREATE TABLE leadevaluations (
	id SERIAL,
	planningmark DOUBLE NOT NULL,
	planningcomment TEXT,
	communicationmark DOUBLE NOT NULL,
	communicationcomment TEXT,
	student_id BIGINT UNSIGNED NOT NULL,
	report_id BIGINT UNSIGNED NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (report_id) REFERENCES reports (id),
	FOREIGN KEY (student_id) REFERENCES students (id)
);

CREATE TABLE individualevaluations (
	id SERIAL,
	mark DOUBLE NOT NULL,
	student_id BIGINT UNSIGNED NOT NULL,
	report_id BIGINT UNSIGNED NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (report_id) REFERENCES reports (id),
	FOREIGN KEY (student_id) REFERENCES students (id)
);

COMMIT;