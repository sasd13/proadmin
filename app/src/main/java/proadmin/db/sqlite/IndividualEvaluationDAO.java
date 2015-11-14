package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.IndividualEvaluation;
import proadmin.beans.Report;
import proadmin.db.IndividualEvaluationTableAccessor;

public class IndividualEvaluationDAO extends SQLiteTableDAO<IndividualEvaluation> implements IndividualEvaluationTableAccessor {

    @Override
    protected ContentValues getContentValues(IndividualEvaluation individualEvaluation) {
        return null;
    }

    @Override
    protected IndividualEvaluation getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public void insert(IndividualEvaluation individualevaluation, Report report) {

    }

    @Override
    public void update(IndividualEvaluation individualevaluation) {

    }

    @Override
    public IndividualEvaluation select(long id) {
        return null;
    }

    @Override
    public List<IndividualEvaluation> selectByReport(Report report) {
        return null;
    }
}
