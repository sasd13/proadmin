package com.sasd13.proadmin.handler;

import android.app.Activity;

/**
 * Created by Samir on 06/03/2016.
 */
public class ActivityHandler {

    public static long getCurrentExtraId(Activity activity, String extraKey) {
        long currentExtraId = activity.getIntent().getLongExtra(extraKey, 0);

        if (currentExtraId == 0) {
            currentExtraId = SessionHandler.getExtraIdFromSession(extraKey);
        } else {
            SessionHandler.setExtraIdInSession(extraKey, currentExtraId);
        }

        return currentExtraId;
    }
}
