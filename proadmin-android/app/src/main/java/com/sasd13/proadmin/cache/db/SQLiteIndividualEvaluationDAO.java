package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.dao.condition.ConditionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteIndividualEvaluationDAO extends SQLiteEntityDAO<IndividualEvaluation> implements IndividualEvaluationDAO {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, individualEvaluation.getId());
        values.put(COLUMN_MARK, individualEvaluation.getMark());
        values.put(COLUMN_STUDENT_ID, individualEvaluation.getStudent().getId());
        values.put(COLUMN_REPORT_ID, individualEvaluation.getReport().getId());

        return values;
    }

    @Override
    protected IndividualEvaluation getCursorValues(Cursor cursor) {
        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_REPORT_ID)));

        IndividualEvaluation individualEvaluation = new IndividualEvaluation(report);
        individualEvaluation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        individualEvaluation.setMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_MARK)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
        individualEvaluation.setStudent(student);

        return individualEvaluation;
    }

    @Override
    public long insert(IndividualEvaluation individualEvaluation) {
        long id = 0;

        db.beginTransaction();

        try {
            id = db.insert(TABLE, null, getContentValues(individualEvaluation));

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(IndividualEvaluation individualEvaluation) {
        if (db.inTransaction()) {
            db.beginTransaction();

            try {
                db.update(TABLE, getContentValues(individualEvaluation), COLUMN_ID + " = ?", new String[]{ String.valueOf(individualEvaluation.getId()) });

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } else {
            db.update(TABLE, getContentValues(individualEvaluation), COLUMN_ID + " = ?", new String[]{ String.valueOf(individualEvaluation.getId()) });
        }
    }

    @Override
    public void delete(IndividualEvaluation individualEvaluation) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + individualEvaluation.getId();

        db.beginTransaction();

        try {
            db.execSQL(query);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public IndividualEvaluation select(long id) {
        IndividualEvaluation individualEvaluation = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            individualEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return individualEvaluation;
    }

    @Override
    public List<IndividualEvaluation> select(Map<String, String[]> parameters) {
        List<IndividualEvaluation> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + ConditionBuilder.parse(parameters, IndividualEvaluationDAO.class) + " AND "
                        + COLUMN_DELETED + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
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
    public List<IndividualEvaluation> selectAll() {
        List<IndividualEvaluation> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(IndividualEvaluation individualEvaluation) {
        if (select(individualEvaluation.getId()) == null) {
            insert(individualEvaluation);
        } else {
            update(individualEvaluation);
        }
    }
}
