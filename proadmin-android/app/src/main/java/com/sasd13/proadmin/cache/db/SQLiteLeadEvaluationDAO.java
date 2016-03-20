package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.util.WhereClauseException;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

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
        values.put(COLUMN_REPORT_ID, leadEvaluation.getReport().getId());
        values.put(COLUMN_STUDENT_ID, leadEvaluation.getStudent().getId());

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
        return db.insert(TABLE, null, getContentValues(leadEvaluation));
    }

    @Override
    public void update(LeadEvaluation leadEvaluation) {
        db.update(TABLE, getContentValues(leadEvaluation), COLUMN_ID + " = ?", new String[]{String.valueOf(leadEvaluation.getId())});
    }

    @Override
    public void delete(long id) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LeadEvaluation select(long id) {
        LeadEvaluation leadEvaluation = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                leadEvaluation = getCursorValues(cursor);
            }
        }
        cursor.close();

        return leadEvaluation;
    }

    @Override
    public List<LeadEvaluation> select(Map<String, String[]> parameters) {
        List<LeadEvaluation> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE " + WhereClauseParser.parse(LeadEvaluationDAO.class, parameters) + ";";

            Cursor cursor = db.rawQuery(query, null);
            fillListWithCursor(list, cursor);
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void fillListWithCursor(List<LeadEvaluation> list, Cursor cursor) {
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
    }

    @Override
    public List<LeadEvaluation> selectAll() {
        List<LeadEvaluation> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        fillListWithCursor(list, cursor);
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
