USE proadmin_aaa;

CREATE TABLE credentials (
	_username VARCHAR(50) NOT NULL,
	_password VARCHAR(255) NOT NULL,
);

CREATE TABLE profiles (
	_id BIGINT NOT NULL AUTO_INCREMENT,
	_userid VARCHAR(255) NOT NULL,
	_roles VARCHAR(255) NOT NULL,
	_intermediary VARCHAR(255) NOT NULL,
	_email VARCHAR(255) NOT NULL,
	_username VARCHAR(50) NOT NULL,
);

COMMIT;