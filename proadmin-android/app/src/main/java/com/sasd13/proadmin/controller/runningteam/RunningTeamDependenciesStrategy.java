package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamDependenciesStrategy extends RequestorStrategy {

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
        allParameters.put(IRunningTeamService.PARAMATERS_RUNNING, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.PARAMETERS_TEAM, new HashMap<String, String[]>());
        allParameters.put(IRunningTeamService.PARAMETERS_ACADEMICLEVEL, new HashMap<String, String[]>());
    }

    public void putParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    @Override
    public Object doInBackgroung(Object in) {
        return service.retrieve(allParameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onRetrieved(((ServiceResult<Map<String, List>>) out).getResult());
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getStringRes());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
