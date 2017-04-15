package com.sasd13.proadmin.controller.student;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentCreateTask extends RequestorTask {

    private StudentController controller;
    private IStudentService service;
    private Map<String, String[]> parameters;

    public StudentCreateTask(StudentController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
        parameters = new HashMap<>();
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result;

        StudentTeam studentTeam = (StudentTeam) in;

        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{studentTeam.getStudent().getNumber()});

        result = service.readStudents(parameters);

        if (result.isSuccess()) {
            if (((ServiceResult<List<Student>>) result).getResult().isEmpty()) {
                result = service.create(studentTeam.getStudent());
            }

            if (result.isSuccess()) {
                result = service.create(studentTeam);
            }
        }

        return result;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onCreateStudent();
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getResID());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
