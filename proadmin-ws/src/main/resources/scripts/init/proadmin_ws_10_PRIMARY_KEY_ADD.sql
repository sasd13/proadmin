SET search_path TO proadmin_ws;

ALTER TABLE projects 
	ADD CONSTRAINT pk_pj 
	PRIMARY KEY (_code);

ALTER TABLE teachers 
	ADD CONSTRAINT pk_tc 
	PRIMARY KEY (_code);

ALTER TABLE students 
	ADD CONSTRAINT pk_st 
	PRIMARY KEY (_code);

ALTER TABLE teams 
	ADD CONSTRAINT pk_tm 
	PRIMARY KEY (_code);

ALTER TABLE studentteams 
	ADD CONSTRAINT pk_sttm 
	PRIMARY KEY (_student_code,_team_code);

ALTER TABLE runnings 
	ADD CONSTRAINT pk_rn 
	PRIMARY KEY (_year,_project_code,_teacher_code);

ALTER TABLE academiclevels 
	ADD CONSTRAINT pk_al 
	PRIMARY KEY (_code);

ALTER TABLE runningteams 
	ADD CONSTRAINT pk_rntm 
	PRIMARY KEY (_running_year,_running_project_code,_running_teacher_code,_team_code,_academiclevel_code);

ALTER TABLE reports 
	ADD CONSTRAINT pk_rp 
	PRIMARY KEY (_code);

ALTER TABLE leadevaluations 
	ADD CONSTRAINT pk_le 
	PRIMARY KEY (_report_code,_student_code);

ALTER TABLE individualevaluations 
	ADD CONSTRAINT pk_ie 
	PRIMARY KEY (_report_code,_student_code);

--COMMIT;