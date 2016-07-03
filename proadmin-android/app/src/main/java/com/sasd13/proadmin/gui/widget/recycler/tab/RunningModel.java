package com.sasd13.proadmin.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IClickable;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.tab.TabModel;
import com.sasd13.proadmin.gui.widget.recycler.RecyclerItemType;

public class RunningModel extends TabModel implements IClickable {

    private String year;
    private Action actionClick;

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.TAB_RUNNING;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;

        setChanged();
        notifyObservers();
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
