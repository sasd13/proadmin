package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.Teacher;
import proadmin.db.TeacherTableAccessor;

public class TeacherDAO extends SQLiteTableDAO<Teacher> implements TeacherTableAccessor {

    @Override
    protected ContentValues getContentValues(Teacher teacher) {
        return null;
    }

    @Override
    protected Teacher getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public void insert(Teacher teacher) {

    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public Teacher select(long id) {
        return null;
    }

    @Override
    public Teacher selectByEmail(String email) {
        return null;
    }
}
