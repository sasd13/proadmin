package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentDeleteStrategy extends RequestorStrategy<StudentTeam, Void> {

    private TeamController controller;
    private IStudentService service;

    public StudentDeleteStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(StudentTeam[] in) {
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
