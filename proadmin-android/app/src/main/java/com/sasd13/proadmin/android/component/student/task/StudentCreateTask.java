package com.sasd13.proadmin.android.component.student.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.student.controller.StudentController;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

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
    private Map<String, Object> criterias;

    public StudentCreateTask(StudentController controller, IStudentService studentService, IStudentTeamService studentTeamService) {
        this.controller = controller;
        this.studentService = studentService;
        this.studentTeamService = studentTeamService;
        criterias = new HashMap<>();
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result;

        criterias.clear();

        StudentTeam studentTeam = (StudentTeam) in;
        Map<String, String[]> map = new HashMap<>();

        map.put(EnumCriteria.INTERMEDIARY.getCode(), new String[]{studentTeam.getStudent().getIntermediary()});
        criterias.put(EnumRestriction.WHERE.getCode(), map);

        result = studentService.read(criterias);

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
