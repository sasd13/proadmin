package com.sasd13.proadmin.util;

import android.content.Context;

import com.sasd13.javaex.service.IServiceCaller;
import com.sasd13.proadmin.util.exception.EnumError;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class ServiceCallerUtils {

    public static void handleErrors(Context context, IServiceCaller serviceCaller, List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        serviceCaller.onError(context.getResources().getString(EnumErrorRes.find(error).getStringRes()));
    }
}
