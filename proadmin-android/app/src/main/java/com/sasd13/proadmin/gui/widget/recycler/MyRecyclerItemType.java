package com.sasd13.proadmin.gui.widget.recycler;

import com.sasd13.androidex.gui.widget.IType;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public enum MyRecyclerItemType implements IType {
    TAB_PROJECT("TAB", "PROJECT"),
    TAB_RUNNING("TAB", "RUNNING"),
    TAB_TEAM("TAB", "TEAM"),
    ;

    private String code, label;

    private MyRecyclerItemType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static MyRecyclerItemType find(String label) {
        for (MyRecyclerItemType type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }

        return null;
    }
}
