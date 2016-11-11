package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.service.AcademicLevelReadService;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class RunningTeamAcademicLevelReadServiceCaller implements IReadServiceCaller<IReadWrapper<AcademicLevel>> {

    private IRunningTeamReadServiceCaller runningTeamReadServiceCaller;
    private AcademicLevelReadService academicLevelReadService;

    public RunningTeamAcademicLevelReadServiceCaller(IRunningTeamReadServiceCaller runningTeamReadServiceCaller) {
        this.runningTeamReadServiceCaller = runningTeamReadServiceCaller;
        academicLevelReadService = new AcademicLevelReadService(this);
    }

    public void readAcademicLevelsFromWS() {
        academicLevelReadService.readAcademicLevels();
    }

    @Override
    public void onLoad() {
        runningTeamReadServiceCaller.onLoad();
    }

    @Override
    public void onReadSucceeded(IReadWrapper<AcademicLevel> academicLevelReadWrapper) {
        runningTeamReadServiceCaller.onReadAcademicLevelsSucceeded(academicLevelReadWrapper);
    }

    @Override
    public void onError(@StringRes int error) {
        runningTeamReadServiceCaller.onError(error);
    }
}
