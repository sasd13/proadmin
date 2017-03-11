SET search_path TO proadmin_ws;

CREATE TRIGGER tr_rp_in
	BEFORE INSERT ON reports 
	FOR EACH ROW 
	EXECUTE PROCEDURE set_rp_code_next();

--COMMIT;