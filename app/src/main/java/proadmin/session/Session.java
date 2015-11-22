package proadmin.session;

import android.content.Context;
import android.content.SharedPreferences;

import proadmin.bean.member.Teacher;
import proadmin.db.DAO;
import proadmin.db.DAOFactory;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_TEACHER_ID = "session_teacher_id";

    private static SharedPreferences preferences;

    protected Session() {}

    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isStarted() {
        return preferences.contains(SESSION_TEACHER_ID);
    }

    public static String getTeacherId() {
        return preferences.getString(SESSION_TEACHER_ID, null);
    }

    public static boolean logIn(String email, String password) {
        DAO dao = DAOFactory.get();

        Teacher teacher = dao.selectTeacherByEmail(email);

        try {
            if (teacher.getPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SESSION_TEACHER_ID, teacher.getId());

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
