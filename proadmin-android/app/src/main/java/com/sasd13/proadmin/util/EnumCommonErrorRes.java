package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumCommonErrorRes {
    ERROR_UNKNOWN(0, "Error unknown"),
    ERROR_PARSING_DATA(-1, "Error parsing data"),
    ERROR_VALIDATING_DATA(-2, "Error validating data"),
    ERROR_SERVICE(-100, "Error service"),
    ;

    private int code;
    private @StringRes
    int stringRes;

    private EnumCommonErrorRes(int code, @StringRes int stringRes) {
        this.code = code;
        this.stringRes = stringRes;
    }

    public int getCode() {
        return code;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumCommonErrorRes find(int code) {
        for (EnumCommonErrorRes commonErrorRes : values()) {
            if (code == commonErrorRes.code) {
                return commonErrorRes;
            }
        }

        return null;
    }
}
