package com.sasd13.proadmin.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IClickable;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.tab.TabModel;
import com.sasd13.proadmin.gui.widget.recycler.RecyclerItemType;

public class TeamModel extends TabModel implements IClickable {

    private Action actionClick;

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.TAB_TEAM;
    }

    @Override
    public Action getActionClick() {
        return actionClick;
    }

    @Override
    public void setActionClick(Action actionClick) {
        this.actionClick = actionClick;

        setChanged();
        notifyObservers();
    }
}
