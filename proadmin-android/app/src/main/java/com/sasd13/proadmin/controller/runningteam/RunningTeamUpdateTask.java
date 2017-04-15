package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamUpdateTask extends RequestorTask {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamUpdateTask(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((RunningTeamUpdateWrapper) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onUpdateRunningTeam();
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getResID());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
