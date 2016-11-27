package com.sasd13.proadmin.util.caller;

import android.support.annotation.StringRes;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class RunningTeamTeamReadServiceCaller implements IReadServiceCaller<IReadWrapper<Team>> {

    private IRunningTeamReadServiceCaller runningTeamReadServiceCaller;
    private TeamReadService teamReadService;

    public RunningTeamTeamReadServiceCaller(IRunningTeamReadServiceCaller runningTeamReadServiceCaller) {
        this.runningTeamReadServiceCaller = runningTeamReadServiceCaller;
        teamReadService = new TeamReadService(this);
    }

    public void readTeamsFromWS() {
        teamReadService.readAll();
    }

    @Override
    public void onLoad() {
        runningTeamReadServiceCaller.onLoad();
    }

    @Override
    public void onReadSucceeded(IReadWrapper<Team> teamReadWrapper) {
        runningTeamReadServiceCaller.onReadTeamsSucceeded(teamReadWrapper);
    }

    @Override
    public void onError(@StringRes int error) {
        runningTeamReadServiceCaller.onError(error);
    }
}
