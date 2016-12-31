package com.sasd13.proadmin.controller.project;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.RunningService;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class RunningServiceCallback implements RunningService.Callback {

    private ProjectController controller;
    private Context context;

    public RunningServiceCallback(ProjectController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onReaded(List<Running> runnings) {
        controller.onReadRunnings(runnings);
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
