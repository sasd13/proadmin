package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamReadStrategy extends ReadRequestorStrategy<Void, List<RunningTeam>> {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamReadStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public List<RunningTeam> doInBackgroung(Void[] voids) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(List<RunningTeam> out) {
        super.onPostExecute(out);

        controller.onReadRunningTeams(out);
    }

    @Override
    public void onCancelled(List<RunningTeam> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
