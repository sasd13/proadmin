USE proadmin_ws;

ALTER TABLE projects ADD CONSTRAINT pk_pj PRIMARY KEY (code);

ALTER TABLE teachers ADD CONSTRAINT pk_tc PRIMARY KEY (code);

ALTER TABLE students ADD CONSTRAINT pk_st PRIMARY KEY (code);

ALTER TABLE teams ADD CONSTRAINT pk_tm PRIMARY KEY (code);

ALTER TABLE studentteams ADD CONSTRAINT pk_sttm PRIMARY KEY (student_code,team_code);

ALTER TABLE runnings ADD CONSTRAINT pk_rn PRIMARY KEY (year,project_code,teacher_code);

ALTER TABLE academiclevels ADD CONSTRAINT pk_al PRIMARY KEY (code);

ALTER TABLE runningteams ADD CONSTRAINT pk_rntm PRIMARY KEY (running_year,running_project_code,running_teacher_code,team_code,academiclevel_code);

ALTER TABLE reports ADD CONSTRAINT pk_rp PRIMARY KEY (code);

ALTER TABLE leadevaluations ADD CONSTRAINT pk_le PRIMARY KEY (report_code,student_code);

ALTER TABLE individualevaluations ADD CONSTRAINT pk_ie PRIMARY KEY (report_code,student_code);

COMMIT;