package com.sasd13.proadmin.controller.runningteam;

import android.content.Context;

import com.sasd13.androidex.ws.rest.promise.MultiReadPromise;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.RunningTeamService;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class RunningTeamServiceCallback implements RunningTeamService.Callback, MultiReadPromise.Callback {

    private RunningTeamController controller;
    private Context context;

    public RunningTeamServiceCallback(RunningTeamController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onReaded(List<RunningTeam> runningTeams) {
        controller.onReadRunningTeams(runningTeams);
    }

    @Override
    public void onReaded(Map<String, List> results) {
        controller.onRetrieved(results);
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
