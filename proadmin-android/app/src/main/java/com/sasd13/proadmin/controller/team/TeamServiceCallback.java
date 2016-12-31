package com.sasd13.proadmin.controller.team;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.TeamService;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class TeamServiceCallback implements TeamService.Callback {

    private TeamController controller;
    private Context context;

    public TeamServiceCallback(TeamController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onReaded(List<Team> teams) {
        controller.onReadTeams(teams);
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.entry();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.entry();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
