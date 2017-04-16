SET search_path TO proadmin_ws;

DELETE FROM individualevaluations;
DELETE FROM leadevaluations;
DELETE FROM reports;
DELETE FROM runningteams;
DELETE FROM runnings;
DELETE FROM studentteams;
DELETE FROM teams;
DELETE FROM students;
DELETE FROM teachers;
DELETE FROM projects;

-- PROJECTS

INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2015-01-01 00:00:00','Projet A','Description du projet A');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2016-01-01 00:00:00','Projet B','Description du projet B');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2016-01-01 00:00:00','Projet C','Description du projet C');

-- TEACHERS

INSERT INTO teachers(_userid,_firstname,_lastname,_email) VALUES ('e448e9244bd8b260e11cade26a7276371adfb50a43276fdb1294cf4c4cada40e','Samir','Said Ali','samir@email.com');

-- STUDENTS

INSERT INTO students(_userid,_firstname,_lastname,_email) VALUES ('Karim','Benzema','benzema@email.com');
INSERT INTO students(_userid,_firstname,_lastname,_email) VALUES ('Cristiano','Ronaldo','ronaldo@email.com');
INSERT INTO students(_userid,_firstname,_lastname,_email) VALUES ('Gareth','Bale','bale@email.com');
INSERT INTO students(_userid,_firstname,_lastname,_email) VALUES ('Luka','Modric','modric@email.com');

-- TEAMS

INSERT INTO teams(_name) VALUES ('Team 1');
INSERT INTO teams(_name) VALUES ('Team 2');

-- STUDENTTEAMS

INSERT INTO studentteams(_student,_team) VALUES ('ST-1','TM-1');
INSERT INTO studentteams(_student,_team) VALUES ('ST-2','TM-1');
INSERT INTO studentteams(_student,_team) VALUES ('ST-3','TM-2');
INSERT INTO studentteams(_student,_team) VALUES ('ST-4','TM-2');

-- RUNNINGS

INSERT INTO runnings(_year,_project,_teacher) VALUES (2015,'PR-1','TC-1');
INSERT INTO runnings(_year,_project,_teacher) VALUES (2016,'PR-2','TC-1');

-- RUNNINGTEAMS

INSERT INTO runningteams(_year,_project,_teacher,_team,_academiclevel) VALUES (2015,'PR-1','TC-1','TM-1','L1');
INSERT INTO runningteams(_year,_project,_teacher,_team,_academiclevel) VALUES (2016,'PR-2','TC-1','TM-2','L2');

-- REPORTS

INSERT INTO reports(_datemeeting,_session,_comment,_year,_project,_teacher,_team,_academiclevel) VALUES (TIMESTAMP '2015-01-01 00:00:00',1,'comment',2015,'PR-1','TC-1','TM-1','L1');

-- LEADEVALUATIONS

INSERT INTO leadevaluations(_planningmark,_planningcomment,_communicationmark,_communicationcomment,_report,_student) VALUES (9,null,10,null,'RP-1','ST-1');

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(_mark,_report,_student) VALUES (7,'RP-1','ST-1');
INSERT INTO individualevaluations(_mark,_report,_student) VALUES (8,'RP-1','ST-2');

--COMMIT;