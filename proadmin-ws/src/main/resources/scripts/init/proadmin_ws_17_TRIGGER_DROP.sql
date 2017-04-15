SET search_path TO proadmin_ws;

DROP TRIGGER IF EXISTS tr_pr_in ON projects;
DROP TRIGGER IF EXISTS tr_tc_in ON teachers;
DROP TRIGGER IF EXISTS tr_st_in ON students;
DROP TRIGGER IF EXISTS tr_tm_in ON teams;
DROP TRIGGER IF EXISTS tr_rp_in ON reports;

--COMMIT;