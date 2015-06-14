package proadmin.session;

import android.content.Context;
import android.content.SharedPreferences;

import proadmin.content.Teacher;
import proadmin.pattern.dao.DataManager;
import proadmin.pattern.dao.accessor.DataAccessor;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_ID = "teacher_id";

    private static SharedPreferences preferences;
    private static DataAccessor dao = DataManager.getDao();

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
        return preferences.contains(SESSION_ID);
    }

    public static String getSessionId() {
        return preferences.getString(SESSION_ID, null);
    }

    public static boolean logIn(String email, String password) {
        dao.open();
        Teacher teacher = dao.selectTeacher(email);
        dao.close();

        if(teacher != null && teacher.getPassword().compareTo(password) == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SESSION_ID, teacher.getId().toString());

            return editor.commit();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_ID);

        return editor.commit();
    }

    public static boolean update(String teacherId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_ID);
        editor.putString(SESSION_ID, teacherId);

        return editor.commit();
    }
}
