package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentCreateStrategy extends RequestorStrategy<StudentTeam, Void> {

    private TeamController controller;
    private IStudentService service;

    public StudentCreateStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(StudentTeam[] in) {
        StudentTeam studentTeam = in[0];
        Map<String, String[]> parameters = new HashMap<>();

        parameters.put(EnumParameter.NUMBER.getName(), new String[]{studentTeam.getStudent().getNumber()});
        List<Student> students = service.readStudents(parameters);

        if (students.isEmpty()) {
            service.create(studentTeam.getStudent());
        }

        service.create(studentTeam);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_saved));
        controller.entry();
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
