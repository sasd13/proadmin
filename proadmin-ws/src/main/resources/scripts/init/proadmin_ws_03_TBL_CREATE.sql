USE proadmin_ws;

CREATE TABLE projects (
	code VARCHAR(50) NOT NULL,
	datecreation VARCHAR(255) NOT NULL,
	title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL
) ENGINE = InnoDB;

CREATE TABLE teachers (
	code VARCHAR(50) NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE students (
	code VARCHAR(50) NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE teams (
	code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE studentteams (
	student_code VARCHAR(50) NOT NULL,
	team_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE runnings (
	year INT NOT NULL,
	project_code VARCHAR(50) NOT NULL,
	teacher_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE academiclevels (
	code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE runningteams (
	running_year INT NOT NULL,
	running_project_code VARCHAR(50) NOT NULL,
	running_teacher_code VARCHAR(50) NOT NULL,
	team_code VARCHAR(50) NOT NULL,
	academiclevel_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE reports (
	code VARCHAR(50) NOT NULL,
	datemeeting VARCHAR(255) NOT NULL,
	session INT NOT NULL,
	comment TEXT,
	runningteam_running_year INT NOT NULL,
	runningteam_running_project_code VARCHAR(50) NOT NULL,
	runningteam_running_teacher_code VARCHAR(50) NOT NULL,
	runningteam_team_code VARCHAR(50) NOT NULL,
	runningteam_academiclevel_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE leadevaluations (
	planningmark NUMERIC(4,2) NOT NULL,
	planningcomment TEXT,
	communicationmark NUMERIC(4,2) NOT NULL,
	communicationcomment TEXT,
	report_code VARCHAR(50) NOT NULL,
	student_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE individualevaluations (
	mark NUMERIC(4,2) NOT NULL,
	report_code VARCHAR(50) NOT NULL,
	student_code VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

COMMIT;