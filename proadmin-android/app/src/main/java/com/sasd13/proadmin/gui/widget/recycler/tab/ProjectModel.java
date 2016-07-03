package com.sasd13.proadmin.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IClickable;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.tab.TabModel;
import com.sasd13.proadmin.gui.widget.recycler.RecyclerItemType;

public class ProjectModel extends TabModel implements IClickable {

    private String code, description;
    private Action actionClick;

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.TAB_PROJECT;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;

        setChanged();
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

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
