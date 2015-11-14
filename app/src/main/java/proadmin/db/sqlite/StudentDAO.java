package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.Student;
import proadmin.db.StudentTableAccessor;

public class StudentDAO extends SQLiteTableDAO<Student> implements StudentTableAccessor {

    @Override
    protected ContentValues getContentValues(Student student) {
        return null;
    }

    @Override
    protected Student getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public long insert(Student student) {
        return 0;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public Student select(long id) {
        return null;
    }

    @Override
    public Student selectByNumber(String number) {
        return null;
    }
}
