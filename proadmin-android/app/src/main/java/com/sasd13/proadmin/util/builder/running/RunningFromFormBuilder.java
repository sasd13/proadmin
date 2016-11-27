package com.sasd13.proadmin.util.builder.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class RunningFromFormBuilder implements IBuilderFromForm<Running> {

    private RunningForm runningForm;

    public RunningFromFormBuilder(RunningForm runningForm) {
        this.runningForm = runningForm;
    }

    @Override
    public Running build() throws FormException {
        Running runningFromForm = new Running();

        runningFromForm.setYear(runningForm.getYear());

        return runningFromForm;
    }
}
