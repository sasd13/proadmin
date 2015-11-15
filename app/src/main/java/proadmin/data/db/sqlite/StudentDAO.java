package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.members.Student;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentDAO extends AbstractTableDAO {

    public static final String STUDENT_TABLE_NAME = "students";

    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_FIRSTNAME = "first_name";
    public static final String STUDENT_LASTNAME = "last_name";
    public static final String STUDENT_EMAIL = "email";

    public long insert(Student student) {
        return db.insert(STUDENT_TABLE_NAME, null, getContentValues(student));
    }

    private ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_ID, student.getId());
        values.put(STUDENT_FIRSTNAME, student.getFirstName());
        values.put(STUDENT_LASTNAME, student.getLastName());
        values.put(STUDENT_EMAIL, student.getEmail());

        return values;
    }

    public long update(Student student) {
        return db.update(STUDENT_TABLE_NAME, getContentValues(student), STUDENT_ID + " = ?", new String[]{student.getId()});
    }

    public Student select(String studentIdOrEmail) {
        Student student = null;

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_ID + ", " + STUDENT_FIRSTNAME + ", " + STUDENT_LASTNAME + ", " + STUDENT_EMAIL
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ? or " + STUDENT_EMAIL + " = ?", new String[]{studentIdOrEmail, studentIdOrEmail});

        if (cursor.moveToNext()) {
            student = new Student();
            student.setId(cursor.getString(0));
            student.setFirstName(cursor.getString(1));
            student.setLastName(cursor.getString(2));
            student.setEmail(cursor.getString(3));
        }
        cursor.close();

        return student;
    }

    public boolean contains(String studentIdOrEmail) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_ID
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ? or " + STUDENT_EMAIL + " = ?", new String[]{studentIdOrEmail, studentIdOrEmail});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
