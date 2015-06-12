package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Id;
import proadmin.content.ListReports;
import proadmin.content.ListStudents;
import proadmin.content.Squad;
import proadmin.content.Project;
import proadmin.content.Teacher;
import proadmin.content.Year;

/**
 * Created by Samir on 02/04/2015.
 */
class SquadDAO extends AbstractDAO {

    public static final String SQUAD_TABLE_NAME = "squads";

    public static final String SQUAD_ID = "squad_id";
    public static final String SQUAD_YEAR = "school_year";
    public static final String SQUAD_PROJECT_ID = "project_id";
    public static final String SQUAD_TEACHER_ID = "teacher_id";

    public long insert(Squad squad) {
        return db.insert(SQUAD_TABLE_NAME, null, getContentValues(squad));
    }

    private ContentValues getContentValues(Squad squad) {
        ContentValues values = new ContentValues();

        values.put(SQUAD_ID, squad.getId().toString());
        values.put(SQUAD_YEAR, squad.getYear().getValue());
        values.put(SQUAD_PROJECT_ID, squad.getProject().getId().toString());
        values.put(SQUAD_TEACHER_ID, squad.getTeacher().getId().toString());

        return values;
    }

    public void update(Squad squad) {
        db.update(SQUAD_TABLE_NAME, getContentValues(squad), SQUAD_ID + " = ?", new String[]{squad.getId().toString()});
    }

    public void delete(Id squadId) {
        db.delete(SQUAD_TABLE_NAME, SQUAD_ID + " = ?", new String[]{squadId.toString()});
    }

    public void deleteAllOfYearAndProject(Year year, Id projectId) {
        db.delete(SQUAD_TABLE_NAME, SQUAD_YEAR + " = ? and " + SQUAD_PROJECT_ID + " = ?", new String[]{year.toString(), projectId.toString()});
    }

    public void deleteAllOfProject(Id projectId) {
        db.delete(SQUAD_TABLE_NAME, SQUAD_PROJECT_ID + " = ?", new String[]{projectId.toString()});
    }

    public void deleteAllOfTeacher(Id teacherId) {
        db.delete(SQUAD_TABLE_NAME, SQUAD_TEACHER_ID + " = ?", new String[]{teacherId.toString()});
    }

    public Squad select(Id squadId) {
        Squad squad = null;

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_YEAR + ", " + SQUAD_PROJECT_ID + ", " + SQUAD_TEACHER_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_ID + " = ?", new String[]{squadId.toString()});

        Id projectId = null, teacherId = null;

        if (cursor.moveToNext()) {
            squad = new Squad();
            squad.setId(squadId);
            squad.setYear(new Year(cursor.getLong(0)));

            projectId = new Id(cursor.getString(1));
            teacherId = new Id(cursor.getString(2));
        }
        cursor.close();

        if (projectId != null) {
            Project project = SQLiteDAO.getInstance().selectProject(projectId);
            squad.setProject(project);
        }

        if (teacherId != null) {
            Teacher teacher = SQLiteDAO.getInstance().selectTeacher(teacherId);
            squad.setTeacher(teacher);
        }

        ListStudents listStudents = SQLiteDAO.getInstance().selectStudents(squadId);
        ListReports listReports = SQLiteDAO.getInstance().selectReports(squadId);

        return squad;
    }

    public boolean contains(Id squadId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_ID + " = ?", new String[]{squadId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
