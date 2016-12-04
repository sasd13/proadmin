package com.sasd13.proadmin.controller.caller.team;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.TeamsActivity;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.TeamsService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class TeamsServiceCaller implements TeamsService.Caller {

    private TeamsActivity teamsActivity;

    public TeamsServiceCaller(TeamsActivity teamsActivity) {
        this.teamsActivity = teamsActivity;
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onReaded(List<Team> teams) {

    }

    @Override
    public void onCreated() {

    }

    @Override
    public void onUpdated() {

    }

    @Override
    public void onDeleted() {

    }

    @Override
    public void onErrors(List<String> errors) {
        teamsActivity.displayMessage(WebServiceUtils.handleErrors(teamsActivity, errors));
    }
}
