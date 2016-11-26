package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.running.RunningReadService;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class RunningTeamProjectReadServiceCaller implements IReadServiceCaller<IReadWrapper<Running>> {

    private IRunningTeamReadServiceCaller runningTeamReadServiceCaller;
    private RunningReadService runningReadService;

    public RunningTeamProjectReadServiceCaller(IRunningTeamReadServiceCaller runningTeamReadServiceCaller) {
        this.runningTeamReadServiceCaller = runningTeamReadServiceCaller;
        runningReadService = new RunningReadService(this);
    }

    public void readRunningsFromWS(int year, String teacherNumber) {
        runningReadService.read(teacherNumber, year);
    }

    @Override
    public void onLoad() {
        runningTeamReadServiceCaller.onLoad();
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Running> runningReadWrapper) {
        runningTeamReadServiceCaller.onReadRunningsSucceeded(runningReadWrapper);
    }

    @Override
    public void onError(@StringRes int error) {
        runningTeamReadServiceCaller.onError(error);
    }
}
