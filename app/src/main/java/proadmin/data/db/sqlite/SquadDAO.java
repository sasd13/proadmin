package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.running.Team;
import proadmin.beans.projects.Project;
import proadmin.beans.members.Teacher;
import proadmin.content.Year;

/**
 * Created by Samir on 02/04/2015.
 */
class SquadDAO extends AbstractTableDAO {

    public static final String SQUAD_TABLE_NAME = "squads";

    public static final String SQUAD_ID = "squad_id";
    public static final String SQUAD_TEACHER_ID = "teacher_id";
    public static final String SQUAD_YEAR = "school_year";
    public static final String SQUAD_PROJECT_ID = "project_id";

    public long insert(Team team) {
        return db.insert(SQUAD_TABLE_NAME, null, getContentValues(team));
    }

    private ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        values.put(SQUAD_ID, team.getId());
        values.put(SQUAD_TEACHER_ID, team.getTeacher().getId());
        values.put(SQUAD_YEAR, team.getYear().getValue());
        values.put(SQUAD_PROJECT_ID, team.getProject().getId());

        return values;
    }

    public long update(Team team) {
        return db.update(SQUAD_TABLE_NAME, getContentValues(team), SQUAD_ID + " = ?", new String[]{team.getId()});
    }

    public long delete(String squadId) {
        return db.delete(SQUAD_TABLE_NAME, SQUAD_ID + " = ?", new String[]{squadId});
    }

    public Team select(String squadId) {
        Team team = null;

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_TEACHER_ID + ", " + SQUAD_YEAR + ", " + SQUAD_PROJECT_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_ID + " = ?", new String[]{squadId});

        String projectId = null;
        String teacherId = null;

        if (cursor.moveToNext()) {
            team = new Team();
            team.setId(squadId);
            team.setYear(new Year(cursor.getLong(0)));

            projectId = new String(cursor.getString(1));
            teacherId = new String(cursor.getString(2));
        }
        cursor.close();

        try {
            Project project = SQLiteDAO.getInstance().selectProject(projectId);
            team.setProject(project);

            Teacher teacher = SQLiteDAO.getInstance().selectTeacher(teacherId);
            team.setTeacher(teacher);

            ListStudents listStudents = SQLiteDAO.getInstance().selectStudentsOfSquad(squadId);
            team.setListStudents(listStudents);

            ListReports listReports = SQLiteDAO.getInstance().selectReportsOfSquad(squadId);
            team.setListReports(listReports);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return team;
    }

    public ListSquads selectAllOfTeacher(String teacherId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ?", new String[]{teacherId});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfYear(Year year) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_YEAR + " = ?", new String[]{year.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfProject(String projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_PROJECT_ID + " = ?", new String[]{projectId});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndYear(String teacherId, Year year) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_YEAR + " = ?",
                new String[]{teacherId, year.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndProject(String teacherId, String projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_PROJECT_ID + " = ?",
                new String[]{teacherId, projectId});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfYearAndProject(Year year, String projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_YEAR + " = ? and " + SQUAD_PROJECT_ID + " = ?", new String[]{year.toString(), projectId});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndYearAndProject(String teacherId, Year year, String projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_YEAR + " = ? and " + SQUAD_PROJECT_ID + " = ?",
                new String[]{teacherId, year.toString(), projectId});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((String) id));
        }

        return listSquads;
    }

    public boolean contains(String squadId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_ID + " = ?", new String[]{squadId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
