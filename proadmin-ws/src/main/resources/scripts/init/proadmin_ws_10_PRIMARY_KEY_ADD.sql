SET search_path TO proadmin_ws;

ALTER TABLE projects ADD CONSTRAINT pk_pj PRIMARY KEY (_code);

ALTER TABLE teachers ADD CONSTRAINT pk_tc PRIMARY KEY (_code);

ALTER TABLE students ADD CONSTRAINT pk_st PRIMARY KEY (_code);

ALTER TABLE teams ADD CONSTRAINT pk_tm PRIMARY KEY (_code);

ALTER TABLE studentteams ADD CONSTRAINT pk_sttm PRIMARY KEY (_student,_team);

ALTER TABLE runnings ADD CONSTRAINT pk_rn PRIMARY KEY (_year,_project,_teacher);

ALTER TABLE academiclevels ADD CONSTRAINT pk_al PRIMARY KEY (_code);

ALTER TABLE runningteams ADD CONSTRAINT pk_rntm PRIMARY KEY (_year,_project,_teacher,_team,_academiclevel);

ALTER TABLE reports ADD CONSTRAINT pk_rp PRIMARY KEY (_code);

ALTER TABLE leadevaluations ADD CONSTRAINT pk_le PRIMARY KEY (_report,_student);

ALTER TABLE individualevaluations ADD CONSTRAINT pk_ie PRIMARY KEY (_report,_student);

--COMMIT;