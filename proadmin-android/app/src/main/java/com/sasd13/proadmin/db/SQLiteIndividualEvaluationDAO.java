package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;

import java.util.List;

public class SQLiteIndividualEvaluationDAO extends SQLiteEntityDAO<IndividualEvaluation> implements IndividualEvaluationDAO {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        ContentValues values = new ContentValues();

        values.put(INDIVIDUALEVALUATION_MARK, individualEvaluation.getMark());
        values.put(STUDENTS_STUDENT_ID, individualEvaluation.getStudent().getId());
        values.put(REPORTS_REPORT_ID, individualEvaluation.getReport().getId());

        return values;
    }

    @Override
    protected IndividualEvaluation getCursorValues(Cursor cursor) {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();

        individualEvaluation.setId(cursor.getLong(cursor.getColumnIndex(INDIVIDUALEVALUATION_ID)));
        individualEvaluation.setMark(cursor.getFloat(cursor.getColumnIndex(INDIVIDUALEVALUATION_MARK)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        individualEvaluation.setStudent(student);

        Report report = new Report();
        report.setId(cursor.getLong(cursor.getColumnIndex(REPORTS_REPORT_ID)));
        individualEvaluation.setReport(report);

        return individualEvaluation;
    }

    @Override
    public long insert(IndividualEvaluation individualEvaluation) {
        long id = executeInsert(INDIVIDUALEVALUATION_TABLE_NAME, individualEvaluation);

        individualEvaluation.setId(id);

        return id;
    }

    @Override
    public void update(IndividualEvaluation individualEvaluation) {
        executeUpdate(INDIVIDUALEVALUATION_TABLE_NAME, individualEvaluation, INDIVIDUALEVALUATION_ID, individualEvaluation.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(INDIVIDUALEVALUATION_TABLE_NAME, INDIVIDUALEVALUATION_ID, id);
    }

    @Override
    public IndividualEvaluation select(long id) {
        String query = "select * from " + INDIVIDUALEVALUATION_TABLE_NAME
                + " where "
                    + INDIVIDUALEVALUATION_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<IndividualEvaluation> selectAll() {
        String query = "select * from " + INDIVIDUALEVALUATION_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public List<IndividualEvaluation> selectByStudent(long id) {
        String query = "select * from " + INDIVIDUALEVALUATION_TABLE_NAME
                + " where "
                    + STUDENTS_STUDENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public List<IndividualEvaluation> selectByReport(long reportId) {
        String query = "select *" + " from " + INDIVIDUALEVALUATION_TABLE_NAME
                + " where "
                    + REPORTS_REPORT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(reportId)});

        return executeSelectMultiResult(cursor);
    }
}
