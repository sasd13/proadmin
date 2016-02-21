package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;

import java.util.List;
import java.util.Map;

public class SQLiteLeadEvaluationDAO extends SQLiteEntityDAO<LeadEvaluation> implements LeadEvaluationDAO {

    @Override
    protected ContentValues getContentValues(LeadEvaluation leadEvaluation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_PLANNINGMARK, leadEvaluation.getPlanningMark());
        values.put(COLUMN_PLANNINGCOMMENT, leadEvaluation.getPlanningComment());
        values.put(COLUMN_COMMUNICATIONMARK, leadEvaluation.getCommunicationMark());
        values.put(COLUMN_COMMUNICATIONCOMMENT, leadEvaluation.getCommunicationComment());
        values.put(COLUMN_REPORT_ID, leadEvaluation.getReport().getId());
        values.put(COLUMN_STUDENT_ID, leadEvaluation.getStudent().getId());

        return values;
    }

    @Override
    protected LeadEvaluation getCursorValues(Cursor cursor) {
        LeadEvaluation leadEvaluation = new LeadEvaluation();

        leadEvaluation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        leadEvaluation.setPlanningMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_PLANNINGMARK)));
        leadEvaluation.setPlanningComment(cursor.getString(cursor.getColumnIndex(COLUMN_PLANNINGCOMMENT)));
        leadEvaluation.setCommunicationMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_COMMUNICATIONMARK)));
        leadEvaluation.setCommunicationComment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMUNICATIONCOMMENT)));

        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_REPORT_ID)));
        leadEvaluation.setReport(report);

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
        leadEvaluation.setStudent(student);

        return leadEvaluation;
    }

    @Override
    public long insert(LeadEvaluation leadEvaluation) {
        long id = executeInsert(TABLE, leadEvaluation);

        leadEvaluation.setId(id);

        return id;
    }

    @Override
    public void update(LeadEvaluation leadEvaluation) {
        executeUpdate(TABLE, leadEvaluation, COLUMN_ID, leadEvaluation.getId());
    }

    @Override
    public void delete(long id) {
        //Do nothing
    }

    @Override
    public LeadEvaluation select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<LeadEvaluation> select(Map<String, String[]> map) {
        return null;
    }

    @Override
    public List<LeadEvaluation> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public void persist(LeadEvaluation leadEvaluation) {
        if (select(leadEvaluation.getId()) == null) {
            insert(leadEvaluation);
        } else {
            update(leadEvaluation);
        }
    }
}
