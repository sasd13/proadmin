package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentHasGroupDAO extends AbstractDAO {

    public static final String STUDENT_HAS_GROUP_TABLE_NAME = "student_has_group";

    public static final String STUDENT_HAS_GROUP_STUDENT_ID = "student_id";
    public static final String STUDENT_HAS_GROUP_GROUP_ID = "group_id";

    public StudentHasGroupDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public long insert(String studentId,String groupId) {
        return mDb.insert(STUDENT_HAS_GROUP_TABLE_NAME, null, getContentValues(studentId, groupId));
    }

    private ContentValues getContentValues(String studentId, String groupId) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_HAS_GROUP_STUDENT_ID, studentId);
        values.put(STUDENT_HAS_GROUP_GROUP_ID, groupId);

        return values;
    }

    public void deleteStudentsIds(String groupId) {
        mDb.delete(STUDENT_HAS_GROUP_TABLE_NAME, STUDENT_HAS_GROUP_GROUP_ID + " = ?", new String[]{groupId});
    }

    public void deleteGroupsIds(String studentId) {
        mDb.delete(STUDENT_HAS_GROUP_TABLE_NAME, STUDENT_HAS_GROUP_STUDENT_ID + " = ?", new String[]{studentId});
    }

    public void delete(String studentId, String groupId) {
        mDb.delete(STUDENT_HAS_GROUP_TABLE_NAME, STUDENT_HAS_GROUP_STUDENT_ID + " = ? and " + STUDENT_HAS_GROUP_GROUP_ID + " = ?", new String[]{studentId, groupId});
    }

    public List<String> selectStudentsIds(String groupId) {
        List<String> listIds = new ArrayList<>();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_GROUP_STUDENT_ID
                        + " from " + STUDENT_HAS_GROUP_TABLE_NAME
                        + " where " + STUDENT_HAS_GROUP_GROUP_ID + " = ?", new String[]{groupId});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        return listIds;
    }

    public List<String> selectGroupsIds(String studentId) {
        List<String> listIds = new ArrayList<>();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_GROUP_GROUP_ID
                        + " from " + STUDENT_HAS_GROUP_TABLE_NAME
                        + " where " + STUDENT_HAS_GROUP_STUDENT_ID + " = ?", new String[]{studentId});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        return listIds;
    }

    public boolean contains(String studentId, String groupId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_GROUP_STUDENT_ID + ", " + STUDENT_HAS_GROUP_GROUP_ID
                        + " from " + STUDENT_HAS_GROUP_TABLE_NAME
                        + " where " + STUDENT_HAS_GROUP_STUDENT_ID + " = ? and " + STUDENT_HAS_GROUP_GROUP_ID + " = ?", new String[]{studentId, groupId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
