package com.sasd13.proadmin.android.view.gui.tab;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public enum TabItemType implements IRecyclerItemType {
    PROJECT("PROJECT_ITEM", ProjectItem.class, RecyclerItemBuilder.class),
    RUNNING("RUNNING_ITEM", RunningItem.class, RecyclerItemBuilder.class),
    RUNNINGTEAM("RUNNINGTEAM_ITEM", RunningTeamItem.class, RecyclerItemBuilder.class),
    TEAM("TEAM_ITEM", TeamItem.class, RecyclerItemBuilder.class),
    STUDENTTEAM("STUDENTTEAM_ITEM", StudentTeamItem.class, RecyclerItemBuilder.class),
    REPORT("REPORT_ITEM", ReportItem.class, RecyclerItemBuilder.class),
    ;

    private String code;
    private Class<? extends RecyclerItem> recyclerItemClass;
    private Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass;

    private TabItemType(String code, Class<? extends RecyclerItem> recyclerItemClass, Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass) {
        this.code = code;
        this.recyclerItemClass = recyclerItemClass;
        this.recyclerItemBuilderClass = recyclerItemBuilderClass;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Class<? extends RecyclerItem> getRecyclerItemClass() {
        return recyclerItemClass;
    }

    @Override
    public Class<? extends RecyclerItemBuilder> getRecyclerItemBuilderClass() {
        return recyclerItemBuilderClass;
    }

    public static TabItemType find(String code) {
        for (TabItemType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }
}
