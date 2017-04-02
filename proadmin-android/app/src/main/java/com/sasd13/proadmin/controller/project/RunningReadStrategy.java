package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IRunningService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningReadStrategy extends ReadRequestorStrategy<Void, List<Running>> {

    private ProjectController controller;
    private IRunningService service;

    public RunningReadStrategy(ProjectController controller, IRunningService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public List<Running> doInBackgroung(Void[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(List<Running> out) {
        super.onPostExecute(out);

        controller.onReadRunnings(out);
    }

    @Override
    public void onCancelled(List<Running> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
