package com.sasd13.proadmin.android.view.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.ISelectable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.model.RunningTeam;

import java.util.Observable;

public class RunningTeamItemModel extends Observable implements IRecyclerItemModel, ILabelizable, ISelectable {

    private RunningTeam runningTeam;
    private boolean selected;

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

    @Override
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        setChanged();
        notifyObservers();
    }
}