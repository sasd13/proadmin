USE proadmin_aaa;

DELETE FROM profiles;
DELETE FROM credentials;

-- CREDENTIALS

INSERT INTO credentials (_username,_password) VALUES ('21010013','2d48a13e67518ded1eb199cf67f8c3c2');

-- PROFILES

INSERT INTO profiles (_userid,_roles,_intermediary,_email,_username) VALUES ('525fd8b503c3a5154e635aca63fdeca3056d7f10cceef49497a995219e39f8bf','PA_TC','10000001','samir@email.com','21010013');
INSERT INTO profiles (_userid,_roles,_intermediary,_email,_username) VALUES ('44a4ed36d9be6468fdbf8cf3f1f8e71dd4d0c50ed01af62cf5e1da33788f4a92','PA_ST','20000001','ronaldo@email.com','21010013');
INSERT INTO profiles (_userid,_roles,_intermediary,_email,_username) VALUES ('44a4ed36d9be6468fdbf8cf3f1f8e71dd4d0c50ed01af62cf5e1da33788f4a92','PA_ST','20000002','benzema@email.com','21010013');
INSERT INTO profiles (_userid,_roles,_intermediary,_email,_username) VALUES ('44a4ed36d9be6468fdbf8cf3f1f8e71dd4d0c50ed01af62cf5e1da33788f4a92','PA_ST','20000003','bale@email.com','21010013');
INSERT INTO profiles (_userid,_roles,_intermediary,_email,_username) VALUES ('44a4ed36d9be6468fdbf8cf3f1f8e71dd4d0c50ed01af62cf5e1da33788f4a92','PA_ST','20000004','modric@email.com','21010013');

COMMIT;