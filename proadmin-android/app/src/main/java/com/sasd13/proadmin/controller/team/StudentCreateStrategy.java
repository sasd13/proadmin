package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
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

public class StudentCreateStrategy extends RequestorStrategy {

    private TeamController controller;
    private IStudentService service;
    private Map<String, String[]> parameters;

    public StudentCreateStrategy(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
        parameters = new HashMap<>();
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        StudentTeam studentTeam = (StudentTeam) in[0];

        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{studentTeam.getStudent().getNumber()});
        List<Student> students = service.readStudents(parameters);

        if (students.isEmpty()) {
            service.create(studentTeam.getStudent());
        }

        service.create(studentTeam);

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
