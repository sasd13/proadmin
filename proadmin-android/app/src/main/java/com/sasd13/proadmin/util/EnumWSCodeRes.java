package com.sasd13.proadmin.util;

import android.support.annotation.StringRes;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.ws.EnumWSCode;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public enum EnumWSCodeRes {
    UNKNOWN                         (EnumWSCode.UNKNOWN,                        R.string.ws_code_0),
    OK                              (EnumWSCode.OK,                             R.string.ws_code_100),
    ERROR_GET                       (EnumWSCode.ERROR_GET,                      R.string.ws_code_error_1),
    ERROR_POST                      (EnumWSCode.ERROR_POST,                     R.string.ws_code_error_2),
    ERROR_PUT                       (EnumWSCode.ERROR_PUT,                      R.string.ws_code_error_3),
    ERROR_DELETE                    (EnumWSCode.ERROR_DELETE,                   R.string.ws_code_error_4),
    ERROR_LOGIN_TEACHER             (EnumWSCode.ERROR_LOGIN_TEACHER,            R.string.ws_code_error_100),
    ;

    private EnumWSCode wsCode;
    private int stringRes;

    EnumWSCodeRes(EnumWSCode wsCode, @StringRes int stringRes) {
        this.wsCode = wsCode;
        this.stringRes = stringRes;
    }

    public EnumWSCode getWSCode() {
        return wsCode;
    }

    public int getStringRes() {
        return stringRes;
    }

    public static EnumWSCodeRes find(EnumWSCode wsCode) {
        for (EnumWSCodeRes wsCodeRes : values()) {
            if (wsCodeRes.wsCode.equals(wsCode)) {
                return wsCodeRes;
            }
        }

        return null;
    }
}
