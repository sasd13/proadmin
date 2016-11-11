package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.service.project.ProjectReadService;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class RunningTeamProjectReadServiceCaller implements IReadServiceCaller<IReadWrapper<Project>> {

    private IRunningTeamReadServiceCaller runningTeamReadServiceCaller;
    private ProjectReadService projectReadService;

    public RunningTeamProjectReadServiceCaller(IRunningTeamReadServiceCaller runningTeamReadServiceCaller) {
        this.runningTeamReadServiceCaller = runningTeamReadServiceCaller;
        projectReadService = new ProjectReadService(this);
    }

    public void readProjectsFromWS() {
        projectReadService.readProjects();
    }

    @Override
    public void onLoad() {
        runningTeamReadServiceCaller.onLoad();
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Project> projectReadWrapper) {
        runningTeamReadServiceCaller.onReadProjectsSucceeded(projectReadWrapper);
    }

    @Override
    public void onError(@StringRes int error) {
        runningTeamReadServiceCaller.onError(error);
    }
}
