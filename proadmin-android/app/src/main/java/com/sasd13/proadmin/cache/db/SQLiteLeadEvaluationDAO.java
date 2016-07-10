package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.dao.condition.ConditionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteLeadEvaluationDAO extends SQLiteEntityDAO<LeadEvaluation> implements LeadEvaluationDAO {

    @Override
    protected ContentValues getContentValues(LeadEvaluation leadEvaluation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, leadEvaluation.getId());
        values.put(COLUMN_PLANNINGMARK, leadEvaluation.getPlanningMark());
        values.put(COLUMN_PLANNINGCOMMENT, leadEvaluation.getPlanningComment());
        values.put(COLUMN_COMMUNICATIONMARK, leadEvaluation.getCommunicationMark());
        values.put(COLUMN_COMMUNICATIONCOMMENT, leadEvaluation.getCommunicationComment());
        values.put(COLUMN_STUDENT_ID, leadEvaluation.getStudent().getId());
        values.put(COLUMN_REPORT_ID, leadEvaluation.getReport().getId());

        return values;
    }

    @Override
    protected LeadEvaluation getCursorValues(Cursor cursor) {
        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_REPORT_ID)));

        LeadEvaluation leadEvaluation = new LeadEvaluation(report);
        leadEvaluation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        leadEvaluation.setPlanningMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_PLANNINGMARK)));
        leadEvaluation.setPlanningComment(cursor.getString(cursor.getColumnIndex(COLUMN_PLANNINGCOMMENT)));
        leadEvaluation.setCommunicationMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_COMMUNICATIONMARK)));
        leadEvaluation.setCommunicationComment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMUNICATIONCOMMENT)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
        leadEvaluation.setStudent(student);

        return leadEvaluation;
    }

    @Override
    public long insert(LeadEvaluation leadEvaluation) {
        long id = 0;

        db.beginTransaction();

        try {
            id = db.insert(TABLE, null, getContentValues(leadEvaluation));

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(LeadEvaluation leadEvaluation) {
        if (db.inTransaction()) {
            db.beginTransaction();

            try {
                db.update(TABLE, getContentValues(leadEvaluation), COLUMN_ID + " = ?", new String[]{ String.valueOf(leadEvaluation.getId()) });

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } else {
            db.update(TABLE, getContentValues(leadEvaluation), COLUMN_ID + " = ?", new String[]{ String.valueOf(leadEvaluation.getId()) });
        }
    }

    @Override
    public void delete(LeadEvaluation leadEvaluation) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + leadEvaluation.getId());

        db.beginTransaction();

        try {
            db.execSQL(builder.toString());

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public LeadEvaluation select(long id) {
        LeadEvaluation leadEvaluation = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            leadEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return leadEvaluation;
    }

    @Override
    public List<LeadEvaluation> select(Map<String, String[]> parameters) {
        List<LeadEvaluation> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, LeadEvaluationDAO.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<LeadEvaluation> selectAll() {
        List<LeadEvaluation> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
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
