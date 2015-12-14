package com.sasd13.proadmin.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.sasd13.wsprovider.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.db.DAOFactory;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_TEACHER_ID = "session_teacher_id";

    private static SharedPreferences preferences;

    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isStarted() {
        return preferences.contains(SESSION_TEACHER_ID);
    }

    public static long getTeacherId() {
        return preferences.getLong(SESSION_TEACHER_ID, 0);
    }

    public static boolean logIn(String number, String password) {
        Teacher teacher = DAOFactory.make().selectTeacherByNumber(number);

        try {
            if (teacher.getPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(SESSION_TEACHER_ID, teacher.getId());

                return editor.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        return editor.commit();
    }
}
