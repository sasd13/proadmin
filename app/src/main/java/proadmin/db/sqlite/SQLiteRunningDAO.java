package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.bean.member.Teacher;
import proadmin.bean.project.Project;
import proadmin.bean.running.Running;

public class SQLiteRunningDAO extends SQLiteTableDAO<Running> implements proadmin.db.RunningDAO {

    @Override
    protected ContentValues getContentValues(Running running) {
        ContentValues values = new ContentValues();

        //values.put(RUNNING_ID, running.getId()); //autoincrement
        values.put(RUNNING_YEAR, running.getYear());
        values.put(TEACHERS_TEACHER_ID, running.getTeacher().getId());
        values.put(PROJECTS_PROJECT_ID, running.getProject().getId());

        return values;
    }

    @Override
    protected Running getCursorValues(Cursor cursor) {
        Running running = new Running();

        running.setId(cursor.getLong(cursor.getColumnIndex(RUNNING_ID)));

        Teacher teacher = new Teacher();
        teacher.setId(cursor.getLong(cursor.getColumnIndex(TEACHERS_TEACHER_ID)));
        running.setTeacher(teacher);

        Project project = new Project();
        project.setId(cursor.getLong(cursor.getColumnIndex(PROJECTS_PROJECT_ID)));
        running.setProject(project);

        return running;
    }

    @Override
    public long insert(Running running) {
        return getDB().insert(RUNNING_TABLE_NAME, null, getContentValues(running));
    }

    @Override
    public void update(Running running) {
        getDB().update(RUNNING_TABLE_NAME, getContentValues(running), RUNNING_ID + " = ?", new String[]{String.valueOf(running.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(RUNNING_TABLE_NAME, RUNNING_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Running select(long id) {
        Running running = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + RUNNING_TABLE_NAME
                        + " where " + RUNNING_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            running = getCursorValues(cursor);
        }
        cursor.close();

        return running;
    }

    @Override
    public List<Running> selectByTeacher(long teacherId) {
        List<Running> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + RUNNING_TABLE_NAME
                        + " where " + TEACHERS_TEACHER_ID + " = ?", new String[]{String.valueOf(teacherId)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
