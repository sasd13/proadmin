package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningBuilder implements IBuilder<Running> {

    private Project project;
    private String teacherNumber;

    public DefaultRunningBuilder(Project project, String teacherNumber) {
        this.project = project;
        this.teacherNumber = teacherNumber;
    }

    @Override
    public Running build() {
        Running running = new Running();

        running.setYear(Calendar.getInstance().get(Calendar.YEAR));
        running.setProject(project);
        running.setTeacher(new Teacher(teacherNumber));

        return running;
    }
}
