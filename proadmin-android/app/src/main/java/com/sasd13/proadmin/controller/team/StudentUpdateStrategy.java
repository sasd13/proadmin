package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentUpdateStrategy extends RequestorStrategy {

    private TeamController controller;
    private IStudentService service;

    public StudentUpdateStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        service.update((StudentUpdateWrapper) in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_updated);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
