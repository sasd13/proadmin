package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.service.IRunningTeamService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamDependenciesStrategy extends RequestorStrategy<Void, Map<String, Object>> {

    private RunningTeamController controller;
    private IRunningTeamService service;
    private Map<String, Map<String, String[]>> allParameters;

    public RunningTeamDependenciesStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
        allParameters = new HashMap<>();
        resetParameters();
    }

    public void resetParameters() {
        allParameters.clear();
        allParameters.put(IRunningTeamService.REQUEST_RUNNINGS, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.REQUEST_TEAMS, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.REQUEST_ACADEMICLEVELS, new HashMap<String, String[]>());
    }

    public void putParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    @Override
    public Map<String, Object> doInBackgroung(Void[] voids) {
        return service.retrieve(allParameters);
    }

    @Override
    public void onPostExecute(Map<String, Object> out) {
        super.onPostExecute(out);

        controller.onRetrieved(out);
    }

    @Override
    public void onCancelled(Map<String, Object> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
