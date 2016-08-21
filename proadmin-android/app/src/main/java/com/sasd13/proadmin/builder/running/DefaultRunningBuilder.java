package com.sasd13.proadmin.builder.running;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.IBuilder;

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
