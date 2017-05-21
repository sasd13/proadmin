package com.sasd13.proadmin.android.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.util.EnumError;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumErrorRes {
    UNKNOWN(EnumError.UNKNOWN, R.string.exception_unknown),
    AUTHENTICATION(EnumError.AUTHENTICATION, R.string.exception_authentication),
    AAA(EnumError.AAA, R.string.exception_aaa),
    WS(EnumError.WS, R.string.exception_ws),
    PARAMETERS(EnumError.PARAMETERS, R.string.exception_parameters),
    PARSER(EnumError.PARSER, R.string.exception_parser),
    SERVICE(EnumError.SERVICE, R.string.exception_service),
    STATUS(EnumError.STATUS, R.string.exception_status),
    CREDENTIAL(EnumError.CREDENTIAL, R.string.exception_credential),;

    private EnumError error;

    @StringRes
    private int resID;

    private EnumErrorRes(EnumError error, @StringRes int resID) {
        this.error = error;
        this.resID = resID;
    }

    public EnumError getError() {
        return error;
    }

    public int getResID() {
        return resID;
    }

    public static EnumErrorRes find(EnumError error) {
        for (EnumErrorRes errorRes : values()) {
            if (errorRes.error.getCode() == error.getCode()) {
                return errorRes;
            }
        }

        return UNKNOWN;
    }

    public static EnumErrorRes find(int code) {
        for (EnumErrorRes errorRes : values()) {
            if (errorRes.error.getCode() == code) {
                return errorRes;
            }
        }

        return UNKNOWN;
    }
}
