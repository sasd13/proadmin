package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamCreateStrategy extends RequestorStrategy {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamCreateStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        service.create((RunningTeam) in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_saved);
        controller.entry();
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
