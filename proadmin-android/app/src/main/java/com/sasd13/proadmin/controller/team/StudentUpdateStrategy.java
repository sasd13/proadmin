package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentUpdateStrategy extends RequestorStrategy<StudentUpdateWrapper, Void> {

    private TeamController controller;
    private IStudentService service;

    public StudentUpdateStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(StudentUpdateWrapper[] in) {
        service.update(in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
