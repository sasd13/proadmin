package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.bean.AcademicLevel;
import proadmin.bean.member.Student;

public class SQLiteStudentDAO extends SQLiteTableDAO<Student> implements proadmin.db.StudentDAO {

    @Override
    protected ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        //values.put(STUDENT_ID, student.getId()); //autoincrement
        values.put(STUDENT_NUMBER, student.getNumber());
        values.put(STUDENT_ACADEMICLEVEL, String.valueOf(student.getAcademicLevel()));
        values.put(STUDENT_FIRSTNAME, student.getFirstName());
        values.put(STUDENT_LASTNAME, student.getLastName());
        values.put(STUDENT_EMAIL, student.getEmail());

        return values;
    }

    @Override
    protected Student getCursorValues(Cursor cursor) {
        Student student = new Student();

        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENT_ID)));
        student.setNumber(cursor.getString(cursor.getColumnIndex(STUDENT_NUMBER)));
        student.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(cursor.getColumnIndex(STUDENT_ACADEMICLEVEL))));
        student.setFirstName(cursor.getString(cursor.getColumnIndex(STUDENT_FIRSTNAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndex(STUDENT_LASTNAME)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(STUDENT_EMAIL)));

        return student;
    }

    @Override
    public long insert(Student student) {
        return getDB().insert(STUDENT_TABLE_NAME, null, getContentValues(student));
    }

    @Override
    public void update(Student student) {
        getDB().update(STUDENT_TABLE_NAME, getContentValues(student), STUDENT_ID + " = ?", new String[]{String.valueOf(student.getId())});
    }

    @Override
    public Student select(long id) {
        Student student = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            student = getCursorValues(cursor);
        }
        cursor.close();

        return student;
    }

    @Override
    public Student selectByNumber(String number) {
        Student student = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_NUMBER + " = ?", new String[]{String.valueOf(number)});

        if (cursor.moveToNext()) {
            student = getCursorValues(cursor);
        }
        cursor.close();

        return student;
    }
}
