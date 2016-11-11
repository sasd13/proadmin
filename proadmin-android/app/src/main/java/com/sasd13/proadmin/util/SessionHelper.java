package com.sasd13.proadmin.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.HomeActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.content.Extra;

/**
 * Created by Samir on 05/03/2016.
 */
public class SessionHelper {

    public static boolean isLogged(Context context) {
        return Session.containsAttribute(context, Extra.TEACHER_NUMBER);
    }

    public static void logIn(final Activity activity, final Teacher teacher) {
        setExtraId(activity, Extra.TEACHER_NUMBER, teacher.getNumber());

        final WaitDialog waitDialog = new WaitDialog(activity);
        final Intent intent = new Intent(activity, HomeActivity.class);

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
        OptionDialog.showOkCancelDialog(
                activity,
                activity.getResources().getString(R.string.button_logout),
                activity.getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit(activity);
                    }
                }
        );
    }

    private static void exit(Activity activity) {
        Session.clear(activity);

        Intent intent = new Intent(activity, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Extra.EXIT, true);

        activity.startActivity(intent);
        activity.finish();
    }

    public static String getExtraId(Context context, String extraKey) {
        return Session.getAttribute(context, extraKey);
    }

    public static void setExtraId(Context context, String extraKey, String id) {
        Session.setAttribute(context, extraKey, String.valueOf(id));
    }

    public static String getIntentExtraId(Activity activity, String extraKey) {
        String currentExtraId = activity.getIntent().getStringExtra(extraKey);

        if (currentExtraId == null) {
            currentExtraId = getExtraId(activity, extraKey);
        } else {
            setExtraId(activity, extraKey, currentExtraId);
        }

        return currentExtraId;
    }
}
