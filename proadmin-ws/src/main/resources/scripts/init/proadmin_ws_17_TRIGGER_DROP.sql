SET search_path TO proadmin_ws;

DROP TRIGGER IF EXISTS tr_ie_in ON individualevaluations;
DROP TRIGGER IF EXISTS tr_le_in ON leadevaluations;
DROP TRIGGER IF EXISTS tr_rp_in ON reports;

--COMMIT;