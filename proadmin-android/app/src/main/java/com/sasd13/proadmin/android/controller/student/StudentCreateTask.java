package com.sasd13.proadmin.android.controller.student;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentCreateTask extends RequestorTask {

    private StudentController controller;
    private IStudentService studentService;
    private IStudentTeamService studentTeamService;
    private Map<String, String[]> parameters;

    public StudentCreateTask(StudentController controller, IStudentService studentService, IStudentTeamService studentTeamService) {
        super();

        this.controller = controller;
        this.studentService = studentService;
        this.studentTeamService = studentTeamService;
        parameters = new HashMap<>();
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result;

        StudentTeam studentTeam = (StudentTeam) in;

        parameters.clear();
        parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[]{studentTeam.getStudent().getIntermediary()});

        result = studentService.read(parameters);

        if (result.isSuccess()) {
            if (((ServiceResult<List<Student>>) result).getData().isEmpty()) {
                result = studentService.create(studentTeam.getStudent());
            }

            if (result.isSuccess()) {
                result = studentTeamService.create(studentTeam);
            }
        }

        return result;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onCreateStudent();
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
