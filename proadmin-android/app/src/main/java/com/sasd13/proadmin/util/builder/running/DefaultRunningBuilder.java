package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Running;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningBuilder implements IBuilder<Running> {

    @Override
    public Running build() {
        Running running = new Running();
        running.setYear(Calendar.getInstance().get(Calendar.YEAR));

        return running;
    }
}
