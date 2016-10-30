package com.sasd13.proadmin.util.ws;

/**
 * Created by Samir on 30/10/2016.
 */
public interface WSResources {

	String URL_AAA = "http://192.168.1.23:8082/proadmin-aaa";
	String URL_AAA_LOGIN = 						URL_AAA + "/login";
	String URL_AAA_SIGN = 						URL_AAA + "/sign";

	String URL_WS = "http://192.168.1.23:8082/proadmin-ws";
	String URL_WS_ACADEMICLEVELS = 				URL_WS + "/academiclevels";
	String URL_WS_INDIVIDUALEVALUATIONS = 		URL_WS + "/individualevaluations";
	String URL_WS_LEADEVALUATIONS = 			URL_WS + "/leadevaluations";
	String URL_WS_PROJECTS = 					URL_WS + "/projects";
	String URL_WS_REPORTS = 					URL_WS + "/reports";
	String URL_WS_RUNNINGS = 					URL_WS + "/runnings";
	String URL_WS_RUNNINGTEAMS = 				URL_WS + "/runningteams";
	String URL_WS_STUDENTS = 					URL_WS + "/students";
	String URL_WS_STUDENTTEAMS = 				URL_WS + "/studentteams";
	String URL_WS_TEACHERS = 					URL_WS + "/teachers";
	String URL_WS_TEAMS = 						URL_WS + "/teams";
}
