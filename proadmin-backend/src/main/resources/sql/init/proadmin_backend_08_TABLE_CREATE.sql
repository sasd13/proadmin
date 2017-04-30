SET search_path TO proadmin_db2;

CREATE TABLE projects (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL,
	_datecreation TIMESTAMP WITH TIME ZONE NOT NULL,
	_title VARCHAR(255) NOT NULL,
	_description TEXT NOT NULL
);

CREATE TABLE teachers (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL,
	_firstname VARCHAR(255) NOT NULL,
	_lastname VARCHAR(255) NOT NULL,
	_email VARCHAR(255) NOT NULL
);

CREATE TABLE students (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL,
	_firstname VARCHAR(255) NOT NULL,
	_lastname VARCHAR(255) NOT NULL,
	_email VARCHAR(255) NOT NULL
);

CREATE TABLE teams (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL,
	_name VARCHAR(255)
);

CREATE TABLE studentteams (
	_id SERIAL NOT NULL,
	_student INT NOT NULL,
	_team INT NOT NULL
);

CREATE TABLE runnings (
	_id SERIAL NOT NULL,
	_year INT NOT NULL,
	_project INT NOT NULL,
	_teacher INT NOT NULL
);

CREATE TABLE academiclevels (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL
);

CREATE TABLE runningteams (
	_id SERIAL NOT NULL,
	_running INT NOT NULL,
	_team INT NOT NULL,
	_academiclevel INT NOT NULL
);

CREATE TABLE reports (
	_id SERIAL NOT NULL,
	_code VARCHAR(255) NOT NULL,
	_datemeeting TIMESTAMP WITH TIME ZONE NOT NULL,
	_session INT NOT NULL,
	_comment TEXT,
	_runningteam INT NOT NULL
);

CREATE TABLE leadevaluations (
	_id SERIAL NOT NULL,
	_planningmark NUMERIC(4,2) NOT NULL,
	_planningcomment TEXT,
	_communicationmark NUMERIC(4,2) NOT NULL,
	_communicationcomment TEXT,
	_report INT NOT NULL,
	_student INT NOT NULL
);

CREATE TABLE individualevaluations (
	_id SERIAL NOT NULL,
	_mark NUMERIC(4,2) NOT NULL,
	_report INT NOT NULL,
	_student INT NOT NULL
);

--COMMIT;