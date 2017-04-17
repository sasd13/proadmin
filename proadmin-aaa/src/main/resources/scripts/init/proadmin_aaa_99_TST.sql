USE proadmin_aaa;

DELETE FROM users;

-- USERS

INSERT INTO users (_uid,_username,_password,_roles,_intermediary,_email) VALUES ('6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','samir@email.com','35093df99d14e975650638b50f7c7402074dd070ea71828a8acb5a2868c1c194','PA_TC;','1','samir@email.com');
INSERT INTO users (_uid,_username,_password,_roles,_intermediary,_email) VALUES ('8f4252a81803b4a7a8eeac961735a3e270ba092d28aac4ef9aa1f376684c33b1','ronaldo@email.com','35093df99d14e975650638b50f7c7402074dd070ea71828a8acb5a2868c1c194','PA_ST;','21010014','ronaldo@email.com');

COMMIT;