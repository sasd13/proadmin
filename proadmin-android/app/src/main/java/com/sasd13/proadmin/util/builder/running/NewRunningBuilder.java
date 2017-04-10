package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewRunningBuilder implements IBuilder<Running> {

    private Project project;
    private Teacher teacher;

    public NewRunningBuilder() {
    }

    public NewRunningBuilder(Project project, String teacherNumber) {
        this.project = project;
        teacher = new Teacher();

        teacher.setNumber(teacherNumber);
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
