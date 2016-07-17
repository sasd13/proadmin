package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.javaex.db.condition.ConditionBuilder;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.condition.IndividualEvaluationConditionExpression;

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
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + individualEvaluation.getId());

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
    public IndividualEvaluation select(long id) {
        IndividualEvaluation individualEvaluation = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
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
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, IndividualEvaluationConditionExpression.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionBuilderException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<IndividualEvaluation> selectAll() {
        List<IndividualEvaluation> list = new ArrayList<>();

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
    public void persist(IndividualEvaluation individualEvaluation) {
        if (select(individualEvaluation.getId()) == null) {
            insert(individualEvaluation);
        } else {
            update(individualEvaluation);
        }
    }
}
