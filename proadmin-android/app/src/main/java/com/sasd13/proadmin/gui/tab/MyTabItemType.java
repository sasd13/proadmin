package com.sasd13.proadmin.gui.tab;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public enum MyTabItemType implements IRecyclerItemType {
    PROJECT("PROJECTITEM", ProjectItem.class, RecyclerItemBuilder.class),
    RUNNING("RUNNINGITEM", RunningItem.class, RecyclerItemBuilder.class),
    TEAM("TEAMITEM", TeamItem.class, RecyclerItemBuilder.class),
    ;

    private String code;
    private Class<? extends RecyclerItem> recyclerItemClass;
    private Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass;

    private MyTabItemType(String code, Class<? extends RecyclerItem> recyclerItemClass, Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass) {
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

    public static MyTabItemType find(String code) {
        for (MyTabItemType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }
}