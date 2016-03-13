package com.sasd13.proadmin.business.running;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.business.BusinessException;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.handler.SessionHandler;
import com.sasd13.proadmin.util.Parameter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningService {

    public static Running initCreateRunning() throws BusinessException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Teacher teacher = Cache.load(SessionHandler.getExtraIdFromSession(Extra.TEACHER_ID), Teacher.class);
        Project project = Cache.load(SessionHandler.getExtraIdFromSession(Extra.PROJECT_ID), Project.class);

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.YEAR.getName(), new String[]{ String.valueOf(year) });
        parameters.put(Parameter.TEACHER.getName(), new String[]{ String.valueOf(teacher.getId()) });
        parameters.put(Parameter.PROJECT.getName(), new String[]{ String.valueOf(project.getId()) });

        List<Running> runnings = Cache.load(parameters, Running.class);
        if (!runnings.isEmpty()) {
            throw new BusinessException("Running error", "Cannot have two runnings for project '" + project.getCode() + "' at year '" + year + "'");
        }

        Running runningToCreate = new Running();

        runningToCreate.setTeacher(teacher);
        runningToCreate.setProject(project);

        return runningToCreate;
    }
}
