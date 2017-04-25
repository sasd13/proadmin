package com.sasd13.proadmin.android.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewRunningBuilder implements IBuilder<Running> {

    private Project project;
    private Teacher teacher;

    public NewRunningBuilder(Project project, String teacherIntermediary) {
        this.project = project;
        teacher = new Teacher();

        teacher.setIntermediary(teacherIntermediary);
    }

    @Override
    public Running build() {
        Running running = new Running();

        running.setYear(Calendar.getInstance().get(Calendar.YEAR));
        running.setProject(project);
        running.setTeacher(teacher);

        return running;
    }
}
