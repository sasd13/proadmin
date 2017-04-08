package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamCreateTask extends RequestorTask {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamCreateTask(RunningTeamController controller, IRunningTeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        return service.create((RunningTeam) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onCreateRunningTeam();
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
