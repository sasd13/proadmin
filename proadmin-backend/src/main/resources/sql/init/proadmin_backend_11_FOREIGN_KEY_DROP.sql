SET search_path TO proadmin_db2;

ALTER TABLE individualevaluations DROP CONSTRAINT IF EXISTS fk_ie_rp;
ALTER TABLE individualevaluations DROP CONSTRAINT IF EXISTS fk_ie_st;

ALTER TABLE leadevaluations DROP CONSTRAINT IF EXISTS fk_le_rp;
ALTER TABLE leadevaluations DROP CONSTRAINT IF EXISTS fk_le_st;

ALTER TABLE reports DROP CONSTRAINT IF EXISTS fk_rp_rntm;

ALTER TABLE runningteams DROP CONSTRAINT IF EXISTS fk_rntm_rn;
ALTER TABLE runningteams DROP CONSTRAINT IF EXISTS fk_rntm_tm;
ALTER TABLE runningteams DROP CONSTRAINT IF EXISTS fk_rntm_al;

ALTER TABLE runnings DROP CONSTRAINT IF EXISTS fk_rn_pr;
ALTER TABLE runnings DROP CONSTRAINT IF EXISTS fk_rn_tc;

ALTER TABLE studentteams DROP CONSTRAINT IF EXISTS fk_sttm_st;
ALTER TABLE studentteams DROP CONSTRAINT IF EXISTS fk_sttm_tm;

--COMMIT;