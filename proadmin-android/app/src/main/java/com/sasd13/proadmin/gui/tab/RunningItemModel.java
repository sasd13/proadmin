package com.sasd13.proadmin.gui.tab;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.bean.running.Running;

import java.util.Observable;

public class RunningItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Running running;

    public RunningItemModel(Running running) {
        this.running = running;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return MyTabItemType.RUNNING;
    }

    @Override
    public String getLabel() {
        return null;
    }

    public String getYear() {
        return String.valueOf(running.getYear());
    }
}
