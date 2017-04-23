SET search_path TO proadmin_db2;

DROP TRIGGER IF EXISTS tr_pr_in ON projects;
DROP TRIGGER IF EXISTS tr_tm_in ON teams;
DROP TRIGGER IF EXISTS tr_rp_in ON reports;

--COMMIT;