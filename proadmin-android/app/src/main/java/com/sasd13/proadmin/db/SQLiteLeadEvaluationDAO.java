package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;

public class SQLiteLeadEvaluationDAO extends SQLiteTableDAO<LeadEvaluation> implements LeadEvaluationDAO {

    @Override
    protected ContentValues getContentValues(LeadEvaluation leadEvaluation) {
        ContentValues values = new ContentValues();

        values.put(LEADEVALUATION_PLANNINGMARK, leadEvaluation.getPlanningMark());
        values.put(LEADEVALUATION_PLANNINGCOMMENT, leadEvaluation.getPlanningComment());
        values.put(LEADEVALUATION_COMMUNICATIONMARK, leadEvaluation.getCommunicationMark());
        values.put(LEADEVALUATION_COMMUNICATIONCOMMENT, leadEvaluation.getCommunicationComment());
        values.put(STUDENTS_STUDENT_ID, leadEvaluation.getStudent().getId());
        values.put(REPORTS_REPORT_ID, leadEvaluation.getReport().getId());

        return values;
    }

    @Override
    protected LeadEvaluation getCursorValues(Cursor cursor) {
        LeadEvaluation leadEvaluation = new LeadEvaluation();

        leadEvaluation.setId(cursor.getLong(cursor.getColumnIndex(LEADEVALUATION_ID)));
        leadEvaluation.setPlanningMark(cursor.getFloat(cursor.getColumnIndex(LEADEVALUATION_PLANNINGMARK)));
        leadEvaluation.setPlanningComment(cursor.getString(cursor.getColumnIndex(LEADEVALUATION_PLANNINGCOMMENT)));
        leadEvaluation.setCommunicationMark(cursor.getFloat(cursor.getColumnIndex(LEADEVALUATION_COMMUNICATIONMARK)));
        leadEvaluation.setCommunicationComment(cursor.getString(cursor.getColumnIndex(LEADEVALUATION_COMMUNICATIONCOMMENT)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        leadEvaluation.setStudent(student);

        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(REPORTS_REPORT_ID)));
        leadEvaluation.setReport(report);

        return leadEvaluation;
    }

    @Override
    public long insert(LeadEvaluation leadEvaluation) {
        return getDB().insert(LEADEVALUATION_TABLE_NAME, null, getContentValues(leadEvaluation));
    }

    @Override
    public void update(LeadEvaluation leadEvaluation) {
        getDB().update(LEADEVALUATION_TABLE_NAME, getContentValues(leadEvaluation), LEADEVALUATION_ID + " = ?", new String[]{String.valueOf(leadEvaluation.getId())});
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
    public LeadEvaluation selectByReport(long reportId) {
        LeadEvaluation leadEvaluation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + LEADEVALUATION_TABLE_NAME
                        + " where " + REPORTS_REPORT_ID + " = ?", new String[]{String.valueOf(reportId)});

        if (cursor.moveToNext()) {
            leadEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return leadEvaluation;
    }
}
