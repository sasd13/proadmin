package com.sasd13.proadmin.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.sasd13.proadmin.core.bean.member.Teacher;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_ID = "session_id";

    private static SharedPreferences preferences;

    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isStarted() {
        return preferences.contains(SESSION_ID);
    }

    public static long getId() {
        return preferences.getLong(SESSION_ID, 0);
    }

    public static void logIn(Teacher teacher) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(SESSION_ID, teacher.getId());
        editor.apply();
    }

    public static void logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
