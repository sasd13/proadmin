SET search_path TO proadmin_db2;

ALTER TABLE projects DROP CONSTRAINT uq_pr;

ALTER TABLE teachers DROP CONSTRAINT uq_tc;

ALTER TABLE students DROP CONSTRAINT uq_st;

ALTER TABLE teams DROP CONSTRAINT uq_tm;

ALTER TABLE studentteams DROP CONSTRAINT uq_sttm;

ALTER TABLE academiclevels DROP CONSTRAINT uq_al;

ALTER TABLE reports DROP CONSTRAINT uq_rp;

--COMMIT;