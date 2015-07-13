package proadmin.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 20/06/2015.
 */
public class IdGenerator {

    private static final String ID_PREFERENCES = "id_preferences";

    private static final String ID_TEACHER = "id_teacher";
    private static final String ID_PROJECT = "id_project";
    private static final String ID_SQUAD = "id_squad";
    private static final String ID_STUDENT = "id_student";
    private static final String ID_REPORT = "id_report";

    public static final String TYPE_TEACHER = "TEACHER";
    public static final String TYPE_PROJECT = "PROJECT";
    public static final String TYPE_SQUAD = "SQUAD";
    public static final String TYPE_STUDENT = "STUDENT";
    public static final String TYPE_REPORT = "REPORT";

    protected IdGenerator() {}

    public static String get(Context context, String type) {
        String id = null, key = null;

        switch (type) {
            case TYPE_TEACHER :
                key = ID_TEACHER;
                id = "teacher-";
                break;
            case TYPE_PROJECT:
                key = ID_PROJECT;
                id = "project-";
                break;
            case TYPE_SQUAD:
                key = ID_SQUAD;
                id = "squad-";
                break;
            case TYPE_STUDENT:
                key = ID_STUDENT;
                id = "student-";
                break;
            case TYPE_REPORT:
                key = ID_REPORT;
                id = "report-";
                break;
        }

        SharedPreferences preferences = context.getSharedPreferences(ID_PREFERENCES, Context.MODE_PRIVATE);

        int count = preferences.getInt(key, 0);
        count++;
        id = id + count;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, count);
        editor.apply();

        return id;
    }
}
