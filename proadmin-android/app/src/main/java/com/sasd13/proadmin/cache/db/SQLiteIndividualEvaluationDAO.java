package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;

import java.util.List;
import java.util.Map;

public class SQLiteIndividualEvaluationDAO extends SQLiteEntityDAO<IndividualEvaluation> implements IndividualEvaluationDAO {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_MARK, individualEvaluation.getMark());
        values.put(COLUMN_REPORT_ID, individualEvaluation.getReport().getId());
        values.put(COLUMN_STUDENT_ID, individualEvaluation.getStudent().getId());

        return values;
    }

    @Override
    protected IndividualEvaluation getCursorValues(Cursor cursor) {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();

        individualEvaluation.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        individualEvaluation.setMark(cursor.getFloat(cursor.getColumnIndex(COLUMN_MARK)));

        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_REPORT_ID)));
        individualEvaluation.setReport(report);

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
        individualEvaluation.setStudent(student);

        return individualEvaluation;
    }

    @Override
    public long insert(IndividualEvaluation individualEvaluation) {
        long id = executeInsert(TABLE, individualEvaluation);

        individualEvaluation.setId(id);

        return id;
    }

    @Override
    public void update(IndividualEvaluation individualEvaluation) {
        executeUpdate(TABLE, individualEvaluation, COLUMN_ID, individualEvaluation.getId());
    }

    @Override
    public void delete(long l) {
        //Do nothing
    }

    @Override
    public IndividualEvaluation select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<IndividualEvaluation> select(Map<String, String[]> map) {
        return null;
    }

    @Override
    public List<IndividualEvaluation> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
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
