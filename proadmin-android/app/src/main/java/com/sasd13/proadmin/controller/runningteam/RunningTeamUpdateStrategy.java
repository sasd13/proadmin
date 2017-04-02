package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamUpdateStrategy extends RequestorStrategy<RunningTeamUpdateWrapper, Void> {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamUpdateStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(RunningTeamUpdateWrapper[] in) {
        service.update(in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
