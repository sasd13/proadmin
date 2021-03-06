package com.sasd13.proadmin.android.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.model.Running;

import java.util.Observable;

public class RunningItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Running running;

    public RunningItemModel(Running running) {
        this.running = running;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.RUNNING;
    }

    @Override
    public String getLabel() {
        return String.valueOf(running.getYear());
    }

    public String getProject() {
        return running.getProject().getCode();
    }
}