package com.sasd13.proadmin.controller.runningteam;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.RunningTeamDependencyService;
import com.sasd13.proadmin.ws.service.RunningTeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class RunningTeamServiceCaller implements RunningTeamService.Caller, RunningTeamDependencyService.RetrieveCaller {

    private RunningTeamController controller;
    private Context context;

    public RunningTeamServiceCaller(RunningTeamController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<RunningTeam> runningTeams) {
        controller.onReadRunningTeams(runningTeams);
    }

    @Override
    public void onRetrieved(List<Running> runnings, List<Team> teams, List<AcademicLevel> academicLevels) {

    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.backPress();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.backPress();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
