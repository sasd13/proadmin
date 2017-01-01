SET search_path TO proadmin_ws;

CREATE TABLE projects (
	_code VARCHAR(50) NOT NULL,
	_datecreation TIMESTAMP NOT NULL,
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
	_student VARCHAR(50) NOT NULL,
	_team VARCHAR(50) NOT NULL
);

CREATE TABLE runnings (
	_year INT NOT NULL,
	_project VARCHAR(50) NOT NULL,
	_teacher VARCHAR(50) NOT NULL
);

CREATE TABLE academiclevels (
	_code VARCHAR(50) NOT NULL
);

CREATE TABLE runningteams (
	_year INT NOT NULL,
	_project VARCHAR(50) NOT NULL,
	_teacher VARCHAR(50) NOT NULL,
	_team VARCHAR(50) NOT NULL,
	_academiclevel VARCHAR(50) NOT NULL
);

CREATE TABLE reports (
	_code VARCHAR(50) NOT NULL,
	_datemeeting TIMESTAMP NOT NULL,
	_session INT NOT NULL,
	_comment TEXT,
	_year INT NOT NULL,
	_project VARCHAR(50) NOT NULL,
	_teacher VARCHAR(50) NOT NULL,
	_team VARCHAR(50) NOT NULL,
	_academiclevel VARCHAR(50) NOT NULL
);

CREATE TABLE leadevaluations (
	_planningmark NUMERIC(4,2) NOT NULL,
	_planningcomment TEXT,
	_communicationmark NUMERIC(4,2) NOT NULL,
	_communicationcomment TEXT,
	_report VARCHAR(50) NOT NULL,
	_student VARCHAR(50) NOT NULL
);

CREATE TABLE individualevaluations (
	_mark NUMERIC(4,2) NOT NULL,
	_report VARCHAR(50) NOT NULL,
	_student VARCHAR(50) NOT NULL
);

--COMMIT;