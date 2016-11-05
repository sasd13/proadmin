package com.sasd13.proadmin.pattern.builder.running;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.pattern.builder.IBuilder;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningBuilder implements IBuilder<Running> {

    private Project project;

    public DefaultRunningBuilder(Project project) {
        this.project = project;
    }

    @Override
    public Running build() {
        Running running = new Running();
        running.setProject(project);
        running.setYear(Calendar.getInstance().get(Calendar.YEAR));

        return running;
    }
}
