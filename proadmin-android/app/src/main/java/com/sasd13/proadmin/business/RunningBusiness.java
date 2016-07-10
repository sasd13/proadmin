package com.sasd13.proadmin.business;

import android.content.Context;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningBusiness {

    public static boolean prepareRunningToCreate(final Running runningToCreate, Context context) {
        boolean prepared = false;

        Teacher teacher = Cache.load(SessionHelper.getExtraIdFromSession(context, Extra.TEACHER_ID), Teacher.class);
        Project project = Cache.load(SessionHelper.getExtraIdFromSession(context, Extra.PROJECT_ID), Project.class);

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.YEAR.getName(), new String[]{ String.valueOf(runningToCreate.getYear()) });
        parameters.put(Parameter.TEACHER.getName(), new String[]{ String.valueOf(teacher.getId()) });
        parameters.put(Parameter.PROJECT.getName(), new String[]{ String.valueOf(project.getId()) });

        List<Running> runnings = Cache.load(parameters, Running.class);
        if (!runnings.isEmpty()) {
            OptionDialog.showOkDialog(
                    context,
                    "Running error",
                    "Cannot have two runnings for project '" + project.getCode() + "' at year '" + runningToCreate.getYear() + "'"
            );
        } else {
            runningToCreate.setTeacher(teacher);
            runningToCreate.setProject(project);

            prepared = true;
        }

        return prepared;
    }
}
