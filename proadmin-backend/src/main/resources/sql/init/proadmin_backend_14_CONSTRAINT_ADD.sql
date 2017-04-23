SET search_path TO proadmin_db2;

ALTER TABLE projects ADD CONSTRAINT uq_pr UNIQUE (_code);

ALTER TABLE teachers ADD CONSTRAINT uq_tc UNIQUE (_code);

ALTER TABLE students ADD CONSTRAINT uq_st UNIQUE (_code);

ALTER TABLE teams ADD CONSTRAINT uq_tm UNIQUE (_code);

ALTER TABLE academiclevels ADD CONSTRAINT uq_al UNIQUE (_code);

ALTER TABLE reports ADD CONSTRAINT uq_rp UNIQUE (_code);

--COMMIT;