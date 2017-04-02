package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamUpdateStrategy extends RequestorStrategy {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamUpdateStrategy(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        service.update((RunningTeamUpdateWrapper) in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_updated);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
