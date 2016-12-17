package com.sasd13.proadmin.controller.team;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.service.ws.TeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class TeamServiceCaller implements TeamService.Caller {

    private TeamController controller;
    private Context context;

    public TeamServiceCaller(TeamController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<Team> teams) {
        controller.onReadTeams(teams);
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
