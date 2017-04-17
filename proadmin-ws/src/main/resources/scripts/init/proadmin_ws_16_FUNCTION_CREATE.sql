SET search_path TO proadmin_ws;

CREATE FUNCTION set_pr_code_next()
	RETURNS TRIGGER AS $set_pr_code_next$
    BEGIN
        NEW._code = 'PR-' || nextval('sq_pr');
        RETURN NEW;
    END;
$set_pr_code_next$ LANGUAGE plpgsql;

CREATE FUNCTION set_tm_code_next()
	RETURNS TRIGGER AS $set_tm_code_next$
    BEGIN
        NEW._code = 'TM-' || nextval('sq_tm');
        RETURN NEW;
    END;
$set_tm_code_next$ LANGUAGE plpgsql;

CREATE FUNCTION set_rp_code_next()
	RETURNS TRIGGER AS $set_rp_code_next$
    BEGIN
        NEW._code = 'RP-' || nextval('sq_rp');
        RETURN NEW;
    END;
$set_rp_code_next$ LANGUAGE plpgsql;

--COMMIT;