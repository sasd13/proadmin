package com.sasd13.proadmin.ws;

/**
 * Created by Samir on 06/03/2016.
 */
public interface WSInformation {

    String URL = "http://192.168.1.23:8082/proadmin-ws";
    String URL_LOGIN =                  URL + "/login";
    String URL_TEACHERS =               URL + "/teachers";
    String URL_PROJECTS =               URL + "/projects";
    String URL_STUDENTS =               URL + "/students";
    String URL_TEAMS =                  URL + "/teams";
    String URL_STUDENTTEAMS =           URL + "/studentteams";
    String URL_RUNNINGS =               URL + "/runnings";
    String URL_RUNNINGTEAMS=            URL + "/runningteams";
    String URL_REPORTS =                URL + "/reports";
    String URL_LEADEVALUATIONS =        URL + "/leadevaluations";
    String URL_INDIVIDUALEVALUATIONS =  URL + "/individualevaluations";
}
