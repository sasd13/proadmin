package com.sasd13.proadmin.util;

import android.content.Context;

import com.sasd13.proadmin.util.exception.EnumError;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class WebServiceUtils {

    public static String handleErrors(Context context, List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        return context.getResources().getString(EnumErrorRes.find(error).getStringRes());
    }
}
