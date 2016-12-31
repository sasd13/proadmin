package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.MultiReadPromise;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

public class ReportDependencyService {

    public static final String CODE_STUDENTTEAMS = "STUDENTTEAMS";

    private MultiReadPromise promise;
    private Map<String, String[]> headers, parametersStudentTeams;

    public ReportDependencyService(MultiReadPromise.Callback callback) {
        promise = new MultiReadPromise(callback);
        headers = new HashMap<>();
        parametersStudentTeams = new HashMap<>();
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
        promise.clearRequests();
        promise.addRequest(CODE_STUDENTTEAMS, StudentTeam.class, WSResources.URL_WS_STUDENTTEAMS, parametersStudentTeams, headers);
        promise.read();
    }
}