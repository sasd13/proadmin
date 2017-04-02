package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IRunningService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningDeleteStrategy extends RequestorStrategy<Running, Void> {

    private ProjectController controller;
    private IRunningService service;

    public RunningDeleteStrategy(ProjectController controller, IRunningService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(Running[] in) {
        service.delete(in);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_deleted));
        controller.entry();
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
