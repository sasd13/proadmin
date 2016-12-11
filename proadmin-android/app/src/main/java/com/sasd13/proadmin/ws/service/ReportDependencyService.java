package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.callback.MultiReadRESTCallback;
import com.sasd13.androidex.ws.rest.service.IWebServiceCaller;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDependencyService implements MultiReadRESTCallback.ReadWebService {

    public interface RetrieveCaller extends IWebServiceCaller {

        void onRetrieved(ResultHolder resultHolder);
    }

    public static class ResultHolder {

        private List<StudentTeam> studentTeams;

        public ResultHolder(List<StudentTeam> studentTeams) {
            this.studentTeams = studentTeams;
        }

        public List<StudentTeam> getStudentTeams() {
            return studentTeams;
        }
    }

    private static final String CODE_STUDENTTEAMS = "STUDENTTEAM";

    private RetrieveCaller caller;
    private MultiReadRESTCallback callback;
    private Map<String, String[]> parametersStudentTeams, headers;

    public ReportDependencyService(RetrieveCaller caller) {
        this.caller = caller;
        callback = new MultiReadRESTCallback(this);
        parametersStudentTeams = new HashMap<>();
        headers = new HashMap<>();
    }

    public void addParameterStudentTeams(String parameter, String[] values) {
        parametersStudentTeams.put(parameter, values);
    }

    public void clearParametersStudentTeams() {
        parametersStudentTeams.clear();
    }

    public void read() {
        headers.clear();
        headers.put(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        callback.addRequest(CODE_STUDENTTEAMS, StudentTeam.class, WSResources.URL_WS_STUDENTTEAMS, parametersStudentTeams, headers);
        callback.read();
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onReaded(Map<String, List> results) {
        List<StudentTeam> studentTeams = (List<StudentTeam>) results.get(CODE_STUDENTTEAMS);

        caller.onRetrieved(new ResultHolder(studentTeams));
    }

    @Override
    public void onErrors(List<String> list) {
        caller.onErrors(list);
    }

    @Override
    public void onErrors(Map<String, List<String>> errors) {
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            caller.onErrors(entry.getValue());
            return;
        }
    }
}