package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Student;
import proadmin.content.id.StudentId;

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

        values.put(STUDENT_ID, student.getId().toString());
        values.put(STUDENT_FIRSTNAME, student.getFirstName());
        values.put(STUDENT_LASTNAME, student.getLastName());
        values.put(STUDENT_EMAIL, student.getEmail());

        return values;
    }

    public long update(Student student) {
        return db.update(STUDENT_TABLE_NAME, getContentValues(student), STUDENT_ID + " = ?", new String[]{student.getId().toString()});
    }

    public Student select(StudentId studentId) {
        Student student = null;

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_FIRSTNAME + ", " + STUDENT_LASTNAME + ", " + STUDENT_EMAIL
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ?", new String[]{studentId.toString()});

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

    public Student select(String email) {
        Student student = null;

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_ID + ", " + STUDENT_FIRSTNAME + ", " + STUDENT_LASTNAME
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            student = new Student();
            student.setId(new StudentId(cursor.getString(0)));
            student.setFirstName(cursor.getString(0));
            student.setLastName(cursor.getString(1));
            student.setEmail(email);
        }
        cursor.close();

        return student;
    }
}
