SET search_path TO proadmin_ws;

CREATE FUNCTION set_rp_code_next()
	RETURNS TRIGGER AS $set_rp_code_next$
    BEGIN
        NEW._code = NEW._code || nextval('sq_rp');
        RETURN NEW;
    END;
$set_rp_code_next$ LANGUAGE plpgsql;

CREATE FUNCTION set_rp_code_curr()
	RETURNS TRIGGER AS $set_rp_code_curr$
    BEGIN
        NEW._report = NEW._report || currval('sq_rp');
        RETURN NEW;
    END;
$set_rp_code_curr$ LANGUAGE plpgsql;

--COMMIT;