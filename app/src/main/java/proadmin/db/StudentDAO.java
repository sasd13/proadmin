package proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.Student;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentDAO extends AbstractDAO {

    public static final String STUDENT_TABLE_NAME = "students";

    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_FIRSTNAME = "first_name";
    public static final String STUDENT_LASTNAME = "last_name";
    public static final String STUDENT_EMAIL = "email";

    public StudentDAO(SQLiteDatabase mDb) {
        super(mDb);
    }

    public long insert(Student student) {
        return mDb.insert(STUDENT_TABLE_NAME, null, getContentValues(student));
    }

    private ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_ID, student.getId());
        values.put(STUDENT_FIRSTNAME, student.getFirstName());
        values.put(STUDENT_LASTNAME, student.getLastName());
        values.put(STUDENT_EMAIL, student.getEmail());

        return values;
    }

    public void update(Student student) {
        mDb.update(STUDENT_TABLE_NAME, getContentValues(student), STUDENT_ID + " = ?", new String[]{student.getId()});
    }

    public void delete(String studentId) {
        mDb.delete(STUDENT_TABLE_NAME, STUDENT_ID + " = ?", new String[]{studentId});
    }

    public Student select(String studentId) {
        Student student = null;

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_FIRSTNAME + ", " + STUDENT_LASTNAME + ", " + STUDENT_EMAIL
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ?", new String[]{studentId});

        if (cursor.moveToNext()) {
            student = new Student();
            student.setId(studentId);
            student.setFirstName(cursor.getString(0));
            student.setLastName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
        }
        cursor.close();

        return student;
    }

    public boolean contains(String studentId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_ID
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ?", new String[]{studentId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
