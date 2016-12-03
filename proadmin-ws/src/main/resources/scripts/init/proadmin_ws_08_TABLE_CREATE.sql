SET search_path TO proadmin_ws;

CREATE TABLE projects (
	_code VARCHAR(50) NOT NULL,
	_datecreation VARCHAR(255) NOT NULL,
	_title VARCHAR(255) NOT NULL,
	_description TEXT NOT NULL
);

CREATE TABLE teachers (
	_code VARCHAR(50) NOT NULL,
	_firstname VARCHAR(255) NOT NULL,
	_lastname VARCHAR(255) NOT NULL,
	_email VARCHAR(255) NOT NULL
);

CREATE TABLE students (
	_code VARCHAR(50) NOT NULL,
	_firstname VARCHAR(255) NOT NULL,
	_lastname VARCHAR(255) NOT NULL,
	_email VARCHAR(255) NOT NULL
);

CREATE TABLE teams (
	_code VARCHAR(50) NOT NULL
);

CREATE TABLE studentteams (
	_student_code VARCHAR(50) NOT NULL,
	_team_code VARCHAR(50) NOT NULL
);

CREATE TABLE runnings (
	_year INT NOT NULL,
	_project_code VARCHAR(50) NOT NULL,
	_teacher_code VARCHAR(50) NOT NULL
);

CREATE TABLE academiclevels (
	_code VARCHAR(50) NOT NULL
);

CREATE TABLE runningteams (
	_running_year INT NOT NULL,
	_running_project_code VARCHAR(50) NOT NULL,
	_running_teacher_code VARCHAR(50) NOT NULL,
	_team_code VARCHAR(50) NOT NULL,
	_academiclevel_code VARCHAR(50) NOT NULL
);

CREATE TABLE reports (
	_code VARCHAR(50) NOT NULL,
	_datemeeting VARCHAR(255) NOT NULL,
	_session INT NOT NULL,
	_comment TEXT,
	_runningteam_running_year INT NOT NULL,
	_runningteam_running_project_code VARCHAR(50) NOT NULL,
	_runningteam_running_teacher_code VARCHAR(50) NOT NULL,
	_runningteam_team_code VARCHAR(50) NOT NULL,
	_runningteam_academiclevel_code VARCHAR(50) NOT NULL
);

CREATE TABLE leadevaluations (
	_planningmark NUMERIC(4,2) NOT NULL,
	_planningcomment TEXT,
	_communicationmark NUMERIC(4,2) NOT NULL,
	_communicationcomment TEXT,
	_report_code VARCHAR(50) NOT NULL,
	_student_code VARCHAR(50) NOT NULL
);

CREATE TABLE individualevaluations (
	_mark NUMERIC(4,2) NOT NULL,
	_report_code VARCHAR(50) NOT NULL,
	_student_code VARCHAR(50) NOT NULL
);

--COMMIT;