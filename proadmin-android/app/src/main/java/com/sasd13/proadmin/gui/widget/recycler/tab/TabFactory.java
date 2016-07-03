package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.proadmin.gui.widget.recycler.RecyclerItemType;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class TabFactory extends com.sasd13.androidex.gui.widget.recycler.tab.TabFactory {

    public TabFactory(Context context) {
        super(context);
    }

    @Override
    public RecyclerModel makeModel(IItemType itemType) {
        if (RecyclerItemType.TAB_PROJECT.equals(itemType)) {
            return new ProjectModel();
        } else if (RecyclerItemType.TAB_RUNNING.equals(itemType)) {
            return new RunningModel();
        } else if (RecyclerItemType.TAB_TEAM.equals(itemType)) {
            return new TeamModel();
        } else {
            return super.makeModel(itemType);
        }
    }

    @Override
    public RecyclerItem makeItem(IItemType itemType) {
        if (RecyclerItemType.TAB_PROJECT.equals(itemType)) {
            return new ProjectItem();
        } else if (RecyclerItemType.TAB_RUNNING.equals(itemType)) {
            return new RunningItem();
        } else if (RecyclerItemType.TAB_TEAM.equals(itemType)) {
            return new TeamItem();
        } else {
            return super.makeItem(itemType);
        }
    }

    @Override
    public RecyclerItemBuilder makeItemBuilder(IItemType itemType) {
        if (RecyclerItemType.TAB_PROJECT.equals(itemType)) {
            return new ProjectItemBuilder(this);
        } else if (RecyclerItemType.TAB_RUNNING.equals(itemType)) {
            return new RunningItemBuilder(this);
        } else if (RecyclerItemType.TAB_TEAM.equals(itemType)) {
            return new TeamItemBuilder(this);
        } else {
            return super.makeItemBuilder(itemType);
        }
    }
}
