package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamDependenciesTask {

    private RunningTeamController controller;
    private IRunningTeamService service;
    private Map<String, Map<String, String[]>> allParameters;

    public RunningTeamDependenciesTask(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
        allParameters = new HashMap<>();

        resetParameters();
    }

    public void resetParameters() {
        allParameters.clear();
        allParameters.put(IRunningTeamService.PARAMATERS_RUNNING, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.PARAMETERS_TEAM, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.PARAMETERS_ACADEMICLEVEL, new HashMap<String, String[]>());
    }

    public void putParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    public void execute() {
        ServiceResult<Map<String, Object>> out = doInBackgroung();

        onPostExecute(out);
    }

    private ServiceResult<Map<String, Object>> doInBackgroung() {
        return service.retrieve(allParameters);
    }

    private void onPostExecute(ServiceResult<Map<String, Object>> out) {
        if (out.isSuccess()) {
            controller.onRetrieved(out.getResult());
        } else {
            controller.display(EnumErrorRes.find(out.getHttpStatus()).getStringRes());
        }
    }
}
