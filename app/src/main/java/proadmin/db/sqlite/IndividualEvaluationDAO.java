package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.beans.IndividualEvaluation;
import proadmin.beans.Report;
import proadmin.beans.Student;
import proadmin.db.IndividualEvaluationTableAccessor;

public class IndividualEvaluationDAO extends SQLiteTableDAO<IndividualEvaluation> implements IndividualEvaluationTableAccessor {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        ContentValues values = new ContentValues();

        //values.put(INDIVIDUALEVALUATION_ID, individualEvaluation.getId()); //autoincrement
        values.put(INDIVIDUALEVALUATION_MARK, individualEvaluation.getMark());
        values.put(STUDENTS_STUDENT_ID, individualEvaluation.getStudent().getId());

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

        return individualEvaluation;
    }

    @Override
    public long insert(IndividualEvaluation individualevaluation, Report report) {
        ContentValues values = getContentValues(individualevaluation);
        values.put(REPORTS_REPORT_ID, report.getId());

        return getDB().insert(INDIVIDUALEVALUATION_TABLE_NAME, null, values);
    }

    @Override
    public void update(IndividualEvaluation individualevaluation) {
        getDB().update(INDIVIDUALEVALUATION_TABLE_NAME, getContentValues(individualevaluation), INDIVIDUALEVALUATION_ID + " = ?", new String[]{String.valueOf(individualevaluation.getId())});
    }

    @Override
    public IndividualEvaluation select(long id) {
        IndividualEvaluation individualEvaluation = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + INDIVIDUALEVALUATION_TABLE_NAME
                        + " where " + INDIVIDUALEVALUATION_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            individualEvaluation = getCursorValues(cursor);
        }
        cursor.close();

        return individualEvaluation;
    }

    @Override
    public List<IndividualEvaluation> selectByReport(Report report) {
        List<IndividualEvaluation> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + INDIVIDUALEVALUATION_TABLE_NAME
                        + " where " + REPORTS_REPORT_ID + " = ?", new String[]{String.valueOf(report.getId())});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
