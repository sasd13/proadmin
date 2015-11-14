package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.beans.LeadEvaluation;
import proadmin.beans.Report;
import proadmin.beans.Student;
import proadmin.db.LeadEvaluationTableAccessor;

public class LeadEvaluationDAO extends SQLiteTableDAO<LeadEvaluation> implements LeadEvaluationTableAccessor {

    @Override
    protected ContentValues getContentValues(LeadEvaluation leadEvaluation) {
        ContentValues values = new ContentValues();

        //values.put(LEADEVALUATION_ID, leadEvaluation.getId()); //autoincrement
        values.put(LEADEVALUATION_PLANNINGMARK, leadEvaluation.getPlanningMark());
        values.put(LEADEVALUATION_PLANNINGCOMMENT, leadEvaluation.getPlanningComment());
        values.put(LEADEVALUATION_COMMUNICATIONMARK, leadEvaluation.getCommunicationMark());
        values.put(LEADEVALUATION_COMMUNICATIONCOMMENT, leadEvaluation.getCommunicationComment());
        values.put(STUDENTS_STUDENT_ID, leadEvaluation.getStudent().getId());

        return values;
    }

    @Override
    protected LeadEvaluation getCursorValues(Cursor cursor) {
        LeadEvaluation leadEvaluation = new LeadEvaluation();

        leadEvaluation.setId(cursor.getLong(cursor.getColumnIndex(LEADEVALUATION_ID)));
        leadEvaluation.setPlanningMark(cursor.getDouble(cursor.getColumnIndex(LEADEVALUATION_PLANNINGMARK)));
        leadEvaluation.setPlanningComment(cursor.getString(cursor.getColumnIndex(LEADEVALUATION_PLANNINGCOMMENT)));
        leadEvaluation.setCommunicationMark(cursor.getLong(cursor.getColumnIndex(LEADEVALUATION_PLANNINGMARK)));
        leadEvaluation.setCommunicationComment(cursor.getString(cursor.getColumnIndex(LEADEVALUATION_COMMUNICATIONCOMMENT)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        leadEvaluation.setStudent(student);

        return leadEvaluation;
    }

    @Override
    public long insert(LeadEvaluation leadevaluation, Report report) {
        ContentValues values = getContentValues(leadevaluation);
        values.put(REPORTS_REPORT_ID, report.getId());

        return getDB().insert(LEADEVALUATION_TABLE_NAME, null, values);
    }

    @Override
    public void update(LeadEvaluation leadevaluation) {
        getDB().update(LEADEVALUATION_TABLE_NAME, getContentValues(leadevaluation), LEADEVALUATION_ID + " = ?", new String[]{String.valueOf(leadevaluation.getId())});
    }

    @Override
    public LeadEvaluation select(long id) {
        LeadEvaluation leadEvaluation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + LEADEVALUATION_TABLE_NAME
                        + " where " + LEADEVALUATION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            leadEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return leadEvaluation;
    }

    @Override
    public List<LeadEvaluation> selectByReport(Report report) {
        List<LeadEvaluation> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + LEADEVALUATION_TABLE_NAME
                        + " where " + REPORTS_REPORT_ID + " = ?", new String[]{String.valueOf(report.getId())});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
