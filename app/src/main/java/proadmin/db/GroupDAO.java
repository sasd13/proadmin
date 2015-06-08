package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Iterator;

import proadmin.content.Group;
import proadmin.content.ListReports;
import proadmin.content.ListStudents;
import proadmin.content.Project;
import proadmin.content.Report;
import proadmin.content.Student;
import proadmin.content.Teacher;

/**
 * Created by Samir on 02/04/2015.
 */
class GroupDAO extends AbstractDAO {

    public static final String GROUP_TABLE_NAME = "group";

    public static final String GROUP_ID = "id";
    public static final String GROUP_YEAR = "year";
    public static final String GROUP_PROJECT_ID = "project_id";
    public static final String GROUP_TEACHER_ID = "teacher_id";

    public GroupDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public long insert(Group group) {
        return mDb.insert(GROUP_TABLE_NAME, null, getContentValues(group));
    }

    private ContentValues getContentValues(Group group) {
        ContentValues values = new ContentValues();

        values.put(GROUP_ID, group.getId());
        values.put(GROUP_YEAR, group.getYear());
        values.put(GROUP_PROJECT_ID, group.getProject().getId());
        values.put(GROUP_TEACHER_ID, group.getTeacher().getId());

        return values;
    }

    public void update(Group group) {
        mDb.update(GROUP_TABLE_NAME, getContentValues(group), GROUP_ID + " = ?", new String[]{group.getId()});
    }

    public void delete(String groupId) {
        mDb.delete(GROUP_TABLE_NAME, GROUP_ID + " = ?", new String[]{groupId});
    }

    public void deleteAllOfProject(String projectId) {
        mDb.delete(GROUP_TABLE_NAME, GROUP_PROJECT_ID + " = ?", new String[]{projectId});
    }

    public void deleteAllOfTeacher(String teacherId) {
        mDb.delete(GROUP_TABLE_NAME, GROUP_TEACHER_ID + " = ?", new String[]{teacherId});
    }

    public Group select(String groupId) {
        Group group = null;

        Cursor cursor = mDb.rawQuery(
                "select " + GROUP_YEAR + ", " + GROUP_PROJECT_ID + ", " + GROUP_TEACHER_ID
                        + " from " + GROUP_TABLE_NAME
                        + " where " + GROUP_ID + " = ?", new String[]{groupId});

        String projectId = null, teacherId = null;

        if (cursor.moveToNext()) {
            group = new Group();
            group.setId(groupId);
            group.setYear(cursor.getLong(0));

            projectId = cursor.getString(1);
            teacherId = cursor.getString(2);
        }
        cursor.close();

        if (projectId != null) {
            Project project = DAO.selectProject(projectId);
            group.setProject(project);
        }

        if (teacherId != null) {
            Teacher teacher = DAO.selectTeacher(teacherId);
            group.setTeacher(teacher);
        }

        return group;
    }

    public boolean contains(String groupId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + GROUP_ID
                        + " from " + GROUP_TABLE_NAME
                        + " where " + GROUP_ID + " = ?", new String[]{groupId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
