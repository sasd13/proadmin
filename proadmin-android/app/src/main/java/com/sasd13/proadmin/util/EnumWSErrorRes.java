package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumWSErrorRes {
    ERROR_UNKNOWN(0, R.string.error_ws_unknown),;

    private int code;
    private
    @StringRes
    int stringRes;

    private EnumWSErrorRes(int code, @StringRes int stringRes) {
        this.code = code;
        this.stringRes = stringRes;
    }

    public int getCode() {
        return code;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumWSErrorRes find(int code) {
        for (EnumWSErrorRes wsErrorRes : values()) {
            if (code == wsErrorRes.code) {
                return wsErrorRes;
            }
        }

        return null;
    }
}
