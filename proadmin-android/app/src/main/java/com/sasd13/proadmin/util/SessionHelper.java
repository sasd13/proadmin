package com.sasd13.proadmin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.activity.LogInActivity;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.content.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged(Context context) {
        return Session.containsAttribute(context, Extra.ID_TEACHER_NUMBER);
    }

    public static void logIn(final Activity activity, final Teacher teacher) {
        setExtraId(activity, Extra.ID_TEACHER_NUMBER, teacher.getNumber());

        final WaitDialog waitDialog = new WaitDialog(activity);
        final Intent intent = new Intent(activity, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(intent);
                waitDialog.dismiss();
                activity.finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

    public static void logOut(final Activity activity) {
        final WaitDialog waitDialog = new WaitDialog(activity);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(new Intent(activity, LogInActivity.class));
                waitDialog.dismiss();
                activity.finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

    public static String getExtraId(Context context, String extraKey) {
        return Session.getAttribute(context, extraKey);
    }

    public static void setExtraId(Context context, String extraKey, String id) {
        Session.setAttribute(context, extraKey, String.valueOf(id));
    }

    public static String getExtraIdTeacherNumber(Context context) {
        return Session.getAttribute(context, Extra.ID_TEACHER_NUMBER);
    }
}
