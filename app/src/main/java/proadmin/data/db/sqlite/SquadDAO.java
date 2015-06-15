package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.id.ListIds;
import proadmin.content.ListReports;
import proadmin.content.ListStudents;
import proadmin.content.Squad;
import proadmin.content.Project;
import proadmin.content.Teacher;
import proadmin.content.Year;
import proadmin.content.ListSquads;
import proadmin.content.id.ProjectId;
import proadmin.content.id.SquadId;
import proadmin.content.id.TeacherId;

/**
 * Created by Samir on 02/04/2015.
 */
class SquadDAO extends AbstractTableDAO {

    public static final String SQUAD_TABLE_NAME = "squads";

    public static final String SQUAD_ID = "squad_id";
    public static final String SQUAD_TEACHER_ID = "teacher_id";
    public static final String SQUAD_YEAR = "school_year";
    public static final String SQUAD_PROJECT_ID = "project_id";

    public long insert(Squad squad) {
        return db.insert(SQUAD_TABLE_NAME, null, getContentValues(squad));
    }

    private ContentValues getContentValues(Squad squad) {
        ContentValues values = new ContentValues();

        values.put(SQUAD_ID, squad.getId().toString());
        values.put(SQUAD_TEACHER_ID, squad.getTeacher().getId().toString());
        values.put(SQUAD_YEAR, squad.getYear().getValue());
        values.put(SQUAD_PROJECT_ID, squad.getProject().getId().toString());

        return values;
    }

    public long update(Squad squad) {
        return db.update(SQUAD_TABLE_NAME, getContentValues(squad), SQUAD_ID + " = ?", new String[]{squad.getId().toString()});
    }

    public long delete(SquadId squadId) {
        return db.delete(SQUAD_TABLE_NAME, SQUAD_ID + " = ?", new String[]{squadId.toString()});
    }

    public Squad select(SquadId squadId) {
        Squad squad = null;

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_TEACHER_ID + ", " + SQUAD_YEAR + ", " + SQUAD_PROJECT_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_ID + " = ?", new String[]{squadId.toString()});

        ProjectId projectId = null;
        TeacherId teacherId = null;

        if (cursor.moveToNext()) {
            squad = new Squad();
            squad.setId(squadId);
            squad.setYear(new Year(cursor.getLong(0)));

            projectId = new ProjectId(cursor.getString(1));
            teacherId = new TeacherId(cursor.getString(2));
        }
        cursor.close();

        try {
            Project project = SQLiteDAO.getInstance().selectProject(projectId);
            squad.setProject(project);

            Teacher teacher = SQLiteDAO.getInstance().selectTeacher(teacherId);
            squad.setTeacher(teacher);

            ListStudents listStudents = SQLiteDAO.getInstance().selectStudentsOfSquad(squadId);
            squad.setListStudents(listStudents);

            ListReports listReports = SQLiteDAO.getInstance().selectReportsOfSquad(squadId);
            squad.setListReports(listReports);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return squad;
    }

    public ListSquads selectAllOfTeacher(TeacherId teacherId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ?", new String[]{teacherId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
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
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfProject(ProjectId projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_PROJECT_ID + " = ?", new String[]{projectId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndYear(TeacherId teacherId, Year year) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_YEAR + " = ?",
                new String[]{teacherId.toString(), year.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndProject(TeacherId teacherId, ProjectId projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_PROJECT_ID + " = ?",
                new String[]{teacherId.toString(), projectId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfYearAndProject(Year year, ProjectId projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_YEAR + " = ? and " + SQUAD_PROJECT_ID + " = ?", new String[]{year.toString(), projectId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public ListSquads selectAllOfTeacherAndYearAndProject(TeacherId teacherId, Year year, ProjectId projectId) {
        ListSquads listSquads = new ListSquads();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + SQUAD_ID
                        + " from " + SQUAD_TABLE_NAME
                        + " where " + SQUAD_TEACHER_ID + " = ? and " + SQUAD_YEAR + " = ? and " + SQUAD_PROJECT_ID + " = ?",
                new String[]{teacherId.toString(), year.toString(), projectId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listSquads.add(select((SquadId) id));
        }

        return listSquads;
    }

    public boolean contains(SquadId squadId) {
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
