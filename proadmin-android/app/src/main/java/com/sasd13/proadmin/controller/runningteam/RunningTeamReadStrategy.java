package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamReadStrategy extends ReadRequestorStrategy {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamReadStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onReadRunningTeams((List<RunningTeam>) out);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
