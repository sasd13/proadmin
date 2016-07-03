package com.sasd13.proadmin.gui.widget.recycler;

import com.sasd13.androidex.gui.widget.IItemType;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public enum RecyclerItemType implements IItemType {
    TAB_PROJECT("TAB_PROJECT"),
    TAB_RUNNING("TAB_RUNNING"),
    TAB_TEAM("TAB_TEAM"),
    ;

    private String code;

    private RecyclerItemType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static RecyclerItemType find(String code) {
        for (RecyclerItemType recyclerItemType : values()) {
            if (recyclerItemType.getCode().equalsIgnoreCase(code)) {
                return  recyclerItemType;
            }
        }

        return null;
    }
}
