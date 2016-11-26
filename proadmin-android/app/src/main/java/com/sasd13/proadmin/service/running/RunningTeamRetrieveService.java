package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;
import com.sasd13.proadmin.ws.wrapper.running.RunningTeamReadWrapper;

public class RunningTeamRetrieveService implements  {

    private IReadServiceCaller<IReadWrapper<RunningTeam>> serviceCaller;
    private ReadTask<RunningTeam> readTask;

    public RunningTeamRetrieveService(IReadServiceCaller<IReadWrapper<RunningTeam>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void read(String teacherNumber) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGTEAMS, this, RunningTeam.class);

        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readTask.execute();
    }

    public void read(Running running) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGTEAMS, this, RunningTeam.class);

        readTask.putParameter(EnumParameter.YEAR.getName(), new String[]{String.valueOf(running.getYear())});
        readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{running.getProject().getCode()});
        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{running.getTeacher().getNumber()});
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(new RunningTeamReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_no_data);
            }
        }
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}