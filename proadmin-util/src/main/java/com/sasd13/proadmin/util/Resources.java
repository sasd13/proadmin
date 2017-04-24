package com.sasd13.proadmin.util;

/**
 * Created by Samir on 30/10/2016.
 */
public interface Resources {

	String URL_AAA = "http://192.168.1.23:8082/proadmin-aaa";
	String URL_AAA_LOGIN = URL_AAA + "/login";
	String URL_AAA_USERS = URL_AAA + "/users";

	String URL_BACKEND = "http://192.168.1.23:8082/proadmin-backend";
	String URL_BACKEND_ACADEMICLEVELS = URL_BACKEND + "/academicLevels";
	String URL_BACKEND_INDIVIDUALEVALUATIONS = URL_BACKEND + "/individualEvaluations";
	String URL_BACKEND_LEADEVALUATIONS = URL_BACKEND + "/leadEvaluations";
	String URL_BACKEND_PROJECTS = URL_BACKEND + "/projects";
	String URL_BACKEND_REPORTS = URL_BACKEND + "/reports";
	String URL_BACKEND_RUNNINGS = URL_BACKEND + "/runnings";
	String URL_BACKEND_RUNNINGTEAMS = URL_BACKEND + "/runningTeams";
	String URL_BACKEND_STUDENTS = URL_BACKEND + "/students";
	String URL_BACKEND_TEACHERS = URL_BACKEND + "/teachers";
	String URL_BACKEND_TEAMS = URL_BACKEND + "/teams";

	String URL_WS = "http://192.168.1.23:8082/proadmin-ws";
	String URL_WS_ACADEMICLEVELS = URL_WS + "/academicLevels";
	String URL_WS_INDIVIDUALEVALUATIONS = URL_WS + "/individualEvaluations";
	String URL_WS_LEADEVALUATIONS = URL_WS + "/leadEvaluations";
	String URL_WS_PROJECTS = URL_WS + "/projects";
	String URL_WS_REPORTS = URL_WS + "/reports";
	String URL_WS_RUNNINGS = URL_WS + "/runnings";
	String URL_WS_RUNNINGTEAMS = URL_WS + "/runningTeams";
	String URL_WS_STUDENTS = URL_WS + "/students";
	String URL_WS_TEACHERS = URL_WS + "/teachers";
	String URL_WS_TEAMS = URL_WS + "/teams";
}
