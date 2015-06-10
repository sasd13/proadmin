package proadmin.session;

import android.content.Context;
import android.content.SharedPreferences;

import proadmin.content.Teacher;
import proadmin.db.DAO;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_KEY = "teacher_id";

    private static SharedPreferences preferences;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean dispose() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        return editor.commit();
    }

    public static boolean isLogged() {
        return preferences.contains(SESSION_KEY);
    }

    public static String getLogin() {
        return preferences.getString(SESSION_KEY, null);
    }

    public static boolean logIn(String email, String password) {
        DAO.open();

        Teacher teacher = DAO.selectTeacher(email, password);

        DAO.close();

        if(teacher != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SESSION_KEY, teacher.getId().toString());

            return editor.commit();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_KEY);

        return editor.commit();
    }

    public static boolean update(String teacherId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_KEY);
        editor.putString(SESSION_KEY, teacherId);

        return editor.commit();
    }
}
