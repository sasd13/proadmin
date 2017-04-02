package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentReadStrategy extends ReadRequestorStrategy<Void, List<StudentTeam>> {

    private TeamController controller;
    private IStudentService service;

    public StudentReadStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public List<StudentTeam> doInBackgroung(Void[] voids) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(List<StudentTeam> out) {
        super.onPostExecute(out);

        controller.onReadStudenTeams(out);
    }

    @Override
    public void onCancelled(List<StudentTeam> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
