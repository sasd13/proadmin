package com.sasd13.proadmin.android.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.model.Team;

import java.util.Observable;

public class TeamItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Team team;

    public TeamItemModel(Team team) {
        this.team = team;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.TEAM;
    }

    @Override
    public String getLabel() {
        return team.getNumber();
    }

    public String getName() {
        return team.getName();
    }
}
