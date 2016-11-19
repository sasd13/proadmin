package com.sasd13.proadmin.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.Observable;

public class RunningTeamItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private RunningTeam runningTeam;

    public RunningTeamItemModel(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.RUNNINGTEAM;
    }

    @Override
    public String getLabel() {
        return runningTeam.getRunning().getProject().getCode();
    }

    public String getTeam() {
        return runningTeam.getTeam().getNumber();
    }
}