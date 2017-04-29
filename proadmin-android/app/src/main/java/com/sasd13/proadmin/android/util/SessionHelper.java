package com.sasd13.proadmin.android.util;

import android.content.Context;

import com.sasd13.androidex.util.Session;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged(Context context) {
        return Session.containsAttribute(context, Extra.INTERMEDIARY);
    }

    public static String getExtra(Context context, String key) {
        return Session.getAttribute(context, key);
    }

    public static void setExtra(Context context, String key, String value) {
        Session.setAttribute(context, key, String.valueOf(value));
    }

    public static String getExtraUserID(Context context) {
        return Session.getAttribute(context, Extra.USERID);
    }

    public static String getExtraIntermediary(Context context) {
        return Session.getAttribute(context, Extra.INTERMEDIARY);
    }

    public static void clear(Context context) {
        Session.clear(context);
    }
}
