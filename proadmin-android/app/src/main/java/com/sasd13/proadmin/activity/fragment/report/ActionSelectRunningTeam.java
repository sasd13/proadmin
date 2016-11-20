package com.sasd13.proadmin.activity.fragment.report;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.tab.RunningTeamItemModel;

/**
 * Created by ssaidali2 on 20/11/2016.
 */

public class ActionSelectRunningTeam implements IAction {

    private RunningTeam runningTeam;
    private RunningTeamItemModel runningTeamItemModel;
    private ReportNewFragment reportNewFragment;

    public ActionSelectRunningTeam(RunningTeam runningTeam, RunningTeamItemModel runningTeamItemModel, ReportNewFragment reportNewFragment) {
        this.runningTeam = runningTeam;
        this.runningTeamItemModel = runningTeamItemModel;
        this.reportNewFragment = reportNewFragment;
    }

    @Override
    public void execute() {
        if (runningTeamItemModel.isSelected()) {
            runningTeamItemModel.setSelected(false);
        } else {
            runningTeamItemModel.setSelected(true);
            reportNewFragment.setRunningTeam(runningTeam);
            reportNewFragment.forward();
        }
    }
}
