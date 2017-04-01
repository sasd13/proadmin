package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

public class ReportDependencyService {

    private static final int NBR_REQUESTS = 3;
    public static final String CODE_STUDENTTEAMS = "STUDENTTEAMS";
    public static final String CODE_LEADEVALUATION = "LEADEVALUATION";
    public static final String CODE_INDIVIDUALEVALUATIONS = "INDIVIDUALEVALUATIONS";

    private MultiReadPromise promiseRead;
    private Map<String, Map<String, String[]>> allParameters;

    public ReportDependencyService() {
        promiseRead = new MultiReadPromise(NBR_REQUESTS);
        allParameters = new HashMap<>();

        resetParameters();
    }

    public void addParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    public void resetParameters() {
        allParameters.clear();
        allParameters.put(CODE_STUDENTTEAMS, new HashMap<String, String[]>());
        allParameters.put(CODE_LEADEVALUATION, new HashMap<String, String[]>());
        allParameters.put(CODE_INDIVIDUALEVALUATIONS, new HashMap<String, String[]>());
    }

    public void read(ICallback callback) {
        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(CODE_STUDENTTEAMS, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        requests[0].setParameters(allParameters.get(CODE_STUDENTTEAMS));

        requests[1] = new MultiReadPromise.Request(CODE_LEADEVALUATION, WSResources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        requests[1].setParameters(allParameters.get(CODE_LEADEVALUATION));

        requests[2] = new MultiReadPromise.Request(CODE_INDIVIDUALEVALUATIONS, WSResources.URL_WS_INDIVIDUALEVALUATIONS, IndividualEvaluation.class);
        requests[2].setParameters(allParameters.get(CODE_INDIVIDUALEVALUATIONS));

        promiseRead.registerCallback(callback);
        promiseRead.execute(requests);
    }
}