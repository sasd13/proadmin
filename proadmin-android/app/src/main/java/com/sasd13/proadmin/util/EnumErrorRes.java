package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.exception.EnumError;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumErrorRes {
    UNKNOWN(EnumError.UNKNOWN, R.string.exception_unknown),
    AAA(EnumError.AAA, R.string.exception_aaa),
    AAA_LOGIN(EnumError.AAA_LOGIN, R.string.exception_aaa_login),
    PARSER(EnumError.PARSER, R.string.exception_parser),
    VALIDATOR(EnumError.VALIDATOR, R.string.exception_validator),
    BUSINESS(EnumError.BUSINESS, R.string.exception_business),
    SERVICE(EnumError.SERVICE, R.string.exception_service),
    WEB_SERVICE(EnumError.WEB_SERVICE, R.string.exception_webservice),
    ;

    private EnumError error;

    @StringRes
    private int stringRes;

    private EnumErrorRes(EnumError error, @StringRes int stringRes) {
        this.error = error;
        this.stringRes = stringRes;
    }

    public EnumError getError() {
        return error;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumErrorRes find(EnumError error) {
        for (EnumErrorRes errorRes : values()) {
            if (errorRes.error.equals(error)) {
                return errorRes;
            }
        }

        return UNKNOWN;
    }
}
