package com.sasd13.proadmin.util.builder.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class RunningTeamFromFormBuilder implements IBuilderFromForm<RunningTeam> {

    private RunningTeamForm runningTeamForm;

    public RunningTeamFromFormBuilder(RunningTeamForm runningTeamForm) {
        this.runningTeamForm = runningTeamForm;
    }

    @Override
    public RunningTeam build() throws FormException {
        RunningTeam runningTeamFromForm = new RunningTeam();

        runningTeamFromForm.setRunning(runningTeamForm.getRunning());
        runningTeamFromForm.setTeam(runningTeamForm.getTeam());
        runningTeamFromForm.setAcademicLevel(runningTeamForm.getAcademicLevel());

        return runningTeamFromForm;
    }
}
