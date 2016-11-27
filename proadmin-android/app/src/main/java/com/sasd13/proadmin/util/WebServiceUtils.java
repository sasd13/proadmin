package com.sasd13.proadmin.util;

import android.content.Context;

import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.ws.caller.IWebServiceCaller;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class WebServiceUtils {

    public static void handleErrors(Context context, IWebServiceCaller caller, List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        caller.onError(context.getResources().getString(EnumErrorRes.find(error).getStringRes()));
    }
}
