package com.sasd13.proadmin.view.gui.browser;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;

/**
 * Created by ssaidali2 on 19/11/2016.
 */

public enum EnumBrowserItemType {
    PROJECTS("PROJECTS", EnumDrawerItemType.NAV),
    TEAMS("TEAMS", EnumDrawerItemType.NAV),
    RUNNINGTEAMS("RUNNINGTEAMS", EnumDrawerItemType.NAV),
    REPORTS("REPORTS", EnumDrawerItemType.NAV),
    SETTINGS("SETTINGS", EnumDrawerItemType.NAV),
    LOGOUT("LOGOUT", EnumDrawerItemType.NAV),
    ;

    private String code;
    private IRecyclerItemType recyclerItemType;

    private EnumBrowserItemType(String code, IRecyclerItemType recyclerItemType) {
        this.code = code;
        this.recyclerItemType = recyclerItemType;
    }

    public String getCode() {
        return code;
    }

    public IRecyclerItemType getRecyclerItemType() {
        return recyclerItemType;
    }

    public static EnumBrowserItemType find(String code) {
        for (EnumBrowserItemType browser : values()) {
            if (browser.code.equalsIgnoreCase(code)) {
                return browser;
            }
        }

        return null;
    }
}
