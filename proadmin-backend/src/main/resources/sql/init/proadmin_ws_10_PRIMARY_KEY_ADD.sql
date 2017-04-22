SET search_path TO proadmin_ws2;

ALTER TABLE projects ADD CONSTRAINT pk_pr PRIMARY KEY (_id);

ALTER TABLE teachers ADD CONSTRAINT pk_tc PRIMARY KEY (_id);

ALTER TABLE students ADD CONSTRAINT pk_st PRIMARY KEY (_id);

ALTER TABLE teams ADD CONSTRAINT pk_tm PRIMARY KEY (_id);

ALTER TABLE studentteams ADD CONSTRAINT pk_sttm PRIMARY KEY (_student,_team);

ALTER TABLE runnings ADD CONSTRAINT pk_rn PRIMARY KEY (_id);

ALTER TABLE academiclevels ADD CONSTRAINT pk_al PRIMARY KEY (_id);

ALTER TABLE runningteams ADD CONSTRAINT pk_rntm PRIMARY KEY (_id);

ALTER TABLE reports ADD CONSTRAINT pk_rp PRIMARY KEY (_id);

ALTER TABLE leadevaluations ADD CONSTRAINT pk_le PRIMARY KEY (_id);

ALTER TABLE individualevaluations ADD CONSTRAINT pk_ie PRIMARY KEY (_id);

--COMMIT;