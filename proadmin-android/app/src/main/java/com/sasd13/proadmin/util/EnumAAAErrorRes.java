package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumAAAErrorRes {
    ERROR_UNKNOWN(0, R.string.error_aaa_unknown),;

    private int code;
    private
    @StringRes
    int stringRes;

    private EnumAAAErrorRes(int code, @StringRes int stringRes) {
        this.code = code;
        this.stringRes = stringRes;
    }

    public int getCode() {
        return code;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumAAAErrorRes find(int code) {
        for (EnumAAAErrorRes aaaErrorRes : values()) {
            if (code == aaaErrorRes.code) {
                return aaaErrorRes;
            }
        }

        return null;
    }
}
