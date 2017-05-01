SET search_path TO proadmin_db2;

CREATE FUNCTION set_pr_code_next() RETURNS TRIGGER AS $set_pr_code_next$
    BEGIN
	    IF NEW._code IS NULL OR trim(NEW._code) = '' THEN
	    	NEW._code = 'PR-' || nextval('proadmin_db2.sq_pr');
	    END IF;
	    
        RETURN NEW;
    END;
$set_pr_code_next$ LANGUAGE plpgsql;

CREATE FUNCTION set_tm_code_next() RETURNS TRIGGER AS $set_tm_code_next$
    BEGIN
	    IF NEW._code IS NULL OR trim(NEW._code) = '' THEN
	    	NEW._code = 'TM-' || nextval('proadmin_db2.sq_tm');
	    END IF;
        
        RETURN NEW;
    END;
$set_tm_code_next$ LANGUAGE plpgsql;

CREATE FUNCTION set_rp_code_next() RETURNS TRIGGER AS $set_rp_code_next$
    BEGIN
	    IF NEW._code IS NULL OR trim(NEW._code) = '' THEN
	    	NEW._code = 'RP-' || nextval('proadmin_db2.sq_rp');
	    END IF;
        
        RETURN NEW;
    END;
$set_rp_code_next$ LANGUAGE plpgsql;

--COMMIT;