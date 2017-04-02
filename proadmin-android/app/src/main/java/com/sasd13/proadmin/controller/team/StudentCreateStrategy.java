package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
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
    public Object doInBackgroung(Object in) {
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
            controller.display(R.string.message_saved);
            controller.entry();
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getStringRes());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
