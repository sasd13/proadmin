package com.sasd13.proadmin.gui.widget.recycler.tab;

import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.proadmin.gui.widget.recycler.MyRecyclerItemType;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class MyTabFactory extends RecyclerFactory {

    @Override
    public RecyclerItem makeItem(IType type) {
        if (MyRecyclerItemType.TAB_PROJECT.equals(type)) {
            return new ProjectItem();
        } else if (MyRecyclerItemType.TAB_RUNNING.equals(type)) {
            return new RunningItem();
        } else if (MyRecyclerItemType.TAB_TEAM.equals(type)) {
            return new TeamItem();
        } else {
            return super.makeItem(type);
        }
    }
}
