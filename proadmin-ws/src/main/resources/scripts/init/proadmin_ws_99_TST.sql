SET search_path TO proadmin_ws;

DELETE FROM individualevaluations;
DELETE FROM leadevaluations;
DELETE FROM reports;
DELETE FROM runningteams;
DELETE FROM academiclevels;
DELETE FROM runnings;
DELETE FROM studentteams;
DELETE FROM teams;
DELETE FROM students;
DELETE FROM teachers;
DELETE FROM projects;

-- PROJECTS

INSERT INTO projects(_code,_datecreation,_title,_description) VALUES ('2015PR01','2015-01-01 00:00:00','Projet A','Description du projet A');
INSERT INTO projects(_code,_datecreation,_title,_description) VALUES ('2016PR02','2016-01-01 00:00:00','Projet B','Description du projet B');
INSERT INTO projects(_code,_datecreation,_title,_description) VALUES ('2016PR03','2016-01-01 00:00:00','Projet C','Description du projet C');

-- TEACHERS

INSERT INTO teachers(_code,_firstname,_lastname,_email) VALUES ('21010013','Samir','Said Ali','samir@email.com');
INSERT INTO teachers(_code,_firstname,_lastname,_email) VALUES ('21010014','Nasri','Samir','nasri@email.com');

-- STUDENTS

INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('2130876','Karim','Benzema','benzema@email.com');
INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('2130877','Cristiano','Ronaldo','ronaldo@email.com');
INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('2130878','Gareth','Bale','bale@email.com');

-- TEAMS

INSERT INTO teams(_code) VALUES ('2015TE01');
INSERT INTO teams(_code) VALUES ('2016TE02');

-- STUDENTTEAMS

INSERT INTO studentteams(_student_code,_team_code) VALUES ('2130876','2015TE01');
INSERT INTO studentteams(_student_code,_team_code) VALUES ('2130877','2015TE01');
INSERT INTO studentteams(_student_code,_team_code) VALUES ('2130878','2016TE02');

-- RUNNINGS

INSERT INTO runnings(_year,_project_code,_teacher_code) VALUES (2015,'2015PR01','21010013');
INSERT INTO runnings(_year,_project_code,_teacher_code) VALUES (2016,'2016PR02','21010014');

-- ACADEMICLEVEL

INSERT INTO academiclevels(_code) VALUES ('L1');
INSERT INTO academiclevels(_code) VALUES ('L2');
INSERT INTO academiclevels(_code) VALUES ('L3');
INSERT INTO academiclevels(_code) VALUES ('M1');
INSERT INTO academiclevels(_code) VALUES ('M2');

-- RUNNINGTEAMS

INSERT INTO runningteams(_running_year,_running_project_code,_running_teacher_code,_team_code,_academiclevel_code) VALUES (2015,'2015PR01','21010013','2015TE01','L1');
INSERT INTO runningteams(_running_year,_running_project_code,_running_teacher_code,_team_code,_academiclevel_code) VALUES (2016,'2016PR02','21010014','2016TE02','L2');

-- REPORTS

INSERT INTO reports(_code,_datemeeting,_session,_comment,_runningteam_running_year,_runningteam_running_project_code,_runningteam_running_teacher_code,_runningteam_team_code,_runningteam_academiclevel_code) VALUES ('RE-','2015-01-01 00:00:00',1,'comment',2015,'2015PR01','21010013','2015TE01','L1');

-- LEADEVALUATIONS

INSERT INTO leadevaluations(_planningmark,_planningcomment,_communicationmark,_communicationcomment,_report_code,_student_code) VALUES (9,null,10,null,'RE-','2130876');

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(_mark,_report_code,_student_code) VALUES (7,'RE-','2130876');
INSERT INTO individualevaluations(_mark,_report_code,_student_code) VALUES (8,'RE-','2130877');

--COMMIT;