package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamCreateStrategy extends RequestorStrategy<RunningTeam, Void> {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamCreateStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(RunningTeam[] in) {
        service.create(in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_saved));
        controller.entry();
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
