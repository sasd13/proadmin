package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IRunningService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningCreateStrategy extends RequestorStrategy {

    private ProjectController controller;
    private IRunningService service;

    public RunningCreateStrategy(ProjectController controller, IRunningService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        service.create(((Running[]) in)[0]);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_saved);
        controller.entry();
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
