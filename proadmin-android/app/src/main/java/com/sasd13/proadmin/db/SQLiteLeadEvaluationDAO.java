package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;

import java.util.List;

public class SQLiteLeadEvaluationDAO extends SQLiteEntityDAO<LeadEvaluation> implements LeadEvaluationDAO {

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
        long id = executeInsert(LEADEVALUATION_TABLE_NAME, leadEvaluation);

        leadEvaluation.setId(id);

        return id;
    }

    @Override
    public void update(LeadEvaluation leadEvaluation) {
        executeUpdate(LEADEVALUATION_TABLE_NAME, leadEvaluation, LEADEVALUATION_ID, leadEvaluation.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(LEADEVALUATION_TABLE_NAME, LEADEVALUATION_ID, id);
    }

    @Override
    public LeadEvaluation select(long id) {
        String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME
                + " WHERE "
                    + LEADEVALUATION_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<LeadEvaluation> selectAll() {
        String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public List<LeadEvaluation> selectByStudent(long studentId) {
        String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME
                + " WHERE "
                    + STUDENTS_STUDENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(studentId)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public LeadEvaluation selectByReport(long reportId) {
        String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME
                + " WHERE "
                    + REPORTS_REPORT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(reportId)});

        return executeSelectSingleResult(cursor);
    }
}
