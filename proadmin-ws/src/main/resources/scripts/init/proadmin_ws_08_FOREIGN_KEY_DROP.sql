USE proadmin_ws;

ALTER TABLE individualevaluations DROP FOREIGN KEY fk_ie_rp_code;
ALTER TABLE individualevaluations DROP FOREIGN KEY fk_ie_st_code;

ALTER TABLE leadevaluations DROP FOREIGN KEY fk_le_rp_code;
ALTER TABLE leadevaluations DROP FOREIGN KEY fk_le_st_code;

ALTER TABLE reports DROP FOREIGN KEY fk_rp_rntm_rn_year;
ALTER TABLE reports DROP FOREIGN KEY fk_rp_rntm_rn_pj_code;
ALTER TABLE reports DROP FOREIGN KEY fk_rp_rntm_rn_tc_code;
ALTER TABLE reports DROP FOREIGN KEY fk_rp_rntm_tm_code;
ALTER TABLE reports DROP FOREIGN KEY fk_rp_rntm_al_code;

ALTER TABLE runningteams DROP FOREIGN KEY fk_rntm_rn_year;
ALTER TABLE runningteams DROP FOREIGN KEY fk_rntm_rn_pj_code;
ALTER TABLE runningteams DROP FOREIGN KEY fk_rntm_rn_tc_code;
ALTER TABLE runningteams DROP FOREIGN KEY fk_rntm_tm_code;
ALTER TABLE runningteams DROP FOREIGN KEY fk_rntm_al_code;

ALTER TABLE runnings DROP FOREIGN KEY fk_rn_pj_code;
ALTER TABLE runnings DROP FOREIGN KEY fk_rn_tc_code;

ALTER TABLE studentteams DROP FOREIGN KEY fk_sttm_st_code;
ALTER TABLE studentteams DROP FOREIGN KEY fk_sttm_tm_code;

COMMIT;