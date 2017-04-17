SET search_path TO proadmin_ws;

CREATE TRIGGER tr_pr_in
	BEFORE INSERT ON projects 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_pr_code_next();
	
CREATE TRIGGER tr_tm_in
	BEFORE INSERT ON teams 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_tm_code_next();
	
CREATE TRIGGER tr_rp_in
	BEFORE INSERT ON reports 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_rp_code_next();

--COMMIT;