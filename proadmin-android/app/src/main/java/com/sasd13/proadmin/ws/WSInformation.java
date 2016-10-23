package com.sasd13.proadmin.ws;

/**
 * Created by Samir on 06/03/2016.
 */
public interface WSInformation {

    String URL_AAA = "http://192.168.1.23:8082/proadmin-aaa";
    String URL_AAA_LOGIN =                  URL_AAA + "/login";

    String URL_WS = "http://192.168.1.23:8082/proadmin-ws";
    String URL_WS_TEACHERS =                URL_WS + "/teachers";
    String URL_WS_PROJECTS =                URL_WS + "/projects";
    String URL_WS_STUDENTS =                URL_WS + "/students";
    String URL_WS_TEAMS =                   URL_WS + "/teams";
    String URL_WS_STUDENTTEAMS =            URL_WS + "/studentteams";
    String URL_WS_RUNNINGS =                URL_WS + "/runnings";
    String URL_WS_RUNNINGTEAMS =            URL_WS + "/runningteams";
    String URL_WS_REPORTS =                 URL_WS + "/reports";
    String URL_WS_LEADEVALUATIONS =         URL_WS + "/leadevaluations";
    String URL_WS_INDIVIDUALEVALUATIONS =   URL_WS + "/individualevaluations";
}
