package com.sasd13.proadmin.util;

import android.app.Activity;
import android.content.Intent;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.HomeActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.content.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged() {
        return Session.containsAttribute(Extra.TEACHER_ID);
    }

    public static void logIn(final Activity activity, final Teacher teacher) {
        setExtraIdInSession(Extra.TEACHER_ID, teacher.getId());

        final WaitDialog waitDialog = new WaitDialog(activity);
        final Intent intent = new Intent(activity, HomeActivity.class);

        waitDialog.setCancelable(false);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(intent);
                waitDialog.dismiss();
                activity.finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();

        waitDialog.show();
    }

    public static void logOut(Activity activity) {
        Session.clear();

        if (HomeActivity.class.equals(activity.getClass())) {
            HomeActivity.self.exit();
        } else {
            Intent intent = HomeActivity.self.getIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Extra.EXIT, true);

            activity.startActivity(intent);
            activity.finish();
        }
    }

    public static long getExtraIdFromSession(String extraKey) {
        return Long.parseLong(Session.getAttribute(extraKey));
    }

    public static void setExtraIdInSession(String extraKey, long id) {
        Session.setAttribute(extraKey, String.valueOf(id));
    }

    public static long getCurrentExtraId(Activity activity, String extraKey) {
        long currentExtraId = activity.getIntent().getLongExtra(extraKey, 0);

        if (currentExtraId == 0) {
            currentExtraId = getExtraIdFromSession(extraKey);
        } else {
            setExtraIdInSession(extraKey, currentExtraId);
        }

        return currentExtraId;
    }
}
