package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.Teacher;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewRunningBuilder implements IBuilder<Running> {

    private Project project;
    private Teacher teacher;

    public NewRunningBuilder(Project project, Teacher teacher) {
        this.project = project;
        this.teacher = teacher;
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
