package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.exception.EnumError;

/**
 * Created by ssaidali2 on 30/10/2016.
 */

public enum EnumErrorRes {
    UNKNOWN(EnumError.UNKNOWN, R.string.error_unknown),
    AAA(EnumError.AAA, R.string.exception_aaa),
    AAA_LOGIN_FAILED(EnumError.AAA_LOGIN_FAILED, R.string.error_aaa_login_failed),
    PARSING_DATA(EnumError.PARSING_DATA, R.string.exception_parsing_data),
    VALIDATING_DATA(EnumError.VALIDATING_DATA, R.string.exception_validating_data),
    SERVICE(EnumError.SERVICE, R.string.exception_service),
    WEBSERVICE(EnumError.WEB_SERVICE, R.string.exception_ws),
    ;

    private EnumError error;
    private @StringRes int stringRes;

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
