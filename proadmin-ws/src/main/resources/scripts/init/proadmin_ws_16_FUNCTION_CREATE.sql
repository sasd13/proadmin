SET search_path TO proadmin_ws;

CREATE FUNCTION set_rp_code_next()
	RETURNS TRIGGER AS $set_rp_code_next$
    BEGIN
        NEW._code = 'RE-' || nextval('sq_rp');
        RETURN NEW;
    END;
$set_rp_code_next$ LANGUAGE plpgsql;

--COMMIT;