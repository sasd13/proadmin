package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.bean.running.IndividualEvaluation;
import proadmin.bean.running.Report;
import proadmin.bean.member.Student;
import proadmin.db.IndividualEvaluationTableAccessor;

public class IndividualEvaluationDAO extends SQLiteTableDAO<IndividualEvaluation> implements IndividualEvaluationTableAccessor {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        ContentValues values = new ContentValues();

        //values.put(INDIVIDUALEVALUATION_ID, individualEvaluation.getId()); //autoincrement
        values.put(INDIVIDUALEVALUATION_MARK, individualEvaluation.getMark());
        values.put(STUDENTS_STUDENT_ID, individualEvaluation.getStudent().getId());
        values.put(REPORTS_REPORT_ID, individualEvaluation.getReport().getId());

        return values;
    }

    @Override
    protected IndividualEvaluation getCursorValues(Cursor cursor) {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();

        individualEvaluation.setId(cursor.getLong(cursor.getColumnIndex(INDIVIDUALEVALUATION_ID)));
        individualEvaluation.setMark(cursor.getDouble(cursor.getColumnIndex(INDIVIDUALEVALUATION_MARK)));

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
        ContentValues values = getContentValues(individualEvaluation);
        values.put(INDIVIDUALEVALUATION_DELETED, false);

        return getDB().insert(INDIVIDUALEVALUATION_TABLE_NAME, null, getContentValues(individualEvaluation));
    }

    @Override
    public void update(IndividualEvaluation individualEvaluation) {
        getDB().update(INDIVIDUALEVALUATION_TABLE_NAME, getContentValues(individualEvaluation), INDIVIDUALEVALUATION_ID + " = ?", new String[]{String.valueOf(individualEvaluation.getId())});
    }

    @Override
    public void deleteByReport(long reportId) {
        List<IndividualEvaluation> list = selectByReport(reportId);

        for (IndividualEvaluation individualEvaluation : list) {
            ContentValues values = getContentValues(individualEvaluation);
            values.put(INDIVIDUALEVALUATION_DELETED, true);

            getDB().update(INDIVIDUALEVALUATION_TABLE_NAME, values, INDIVIDUALEVALUATION_ID + " = ?", new String[]{String.valueOf(individualEvaluation.getId())});
        }
    }

    @Override
    public IndividualEvaluation select(long id) {
        IndividualEvaluation individualEvaluation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + INDIVIDUALEVALUATION_TABLE_NAME
                        + " where " + INDIVIDUALEVALUATION_ID + " = ?" + getConditionDeleted(), new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            individualEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return individualEvaluation;
    }

    private String getConditionDeleted() {
        return " and " + INDIVIDUALEVALUATION_DELETED + " = false";
    }

    @Override
    public List<IndividualEvaluation> selectByReport(long reportId) {
        List<IndividualEvaluation> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + INDIVIDUALEVALUATION_TABLE_NAME
                        + " where " + REPORTS_REPORT_ID + " = ?" + getConditionDeleted(), new String[]{String.valueOf(reportId)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
