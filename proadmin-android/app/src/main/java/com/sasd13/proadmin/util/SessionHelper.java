package com.sasd13.proadmin.util;

import android.app.Activity;

import com.sasd13.androidex.session.Session;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static void logOut(Activity activity) {
        Session.clear();
        ActivityHelper.goToHomeActivityAndExit(activity);
    }

    public static long getExtraIdFromSession(String extraKey) {
        return Long.parseLong(Session.getAttribute(extraKey));
    }

    public static void setExtraIdInSession(String extraKey, long id) {
        Session.setAttribute(extraKey, String.valueOf(id));
    }
}
