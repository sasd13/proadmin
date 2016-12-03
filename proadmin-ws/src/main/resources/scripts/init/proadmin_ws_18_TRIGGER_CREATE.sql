SET search_path TO proadmin_ws;

CREATE TRIGGER tr_rp_in
	BEFORE INSERT ON reports 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_rp_code_next();
	
CREATE TRIGGER tr_le_in
	BEFORE INSERT ON leadevaluations 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_rp_code_curr();
	
CREATE TRIGGER tr_ie_in
	BEFORE INSERT ON individualevaluations 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_rp_code_curr();

--COMMIT;