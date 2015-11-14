package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.LeadEvaluation;
import proadmin.beans.Report;
import proadmin.db.LeadEvaluationTableAccessor;

public class LeadEvaluationDAO extends SQLiteTableDAO<LeadEvaluation> implements LeadEvaluationTableAccessor {

    @Override
    protected ContentValues getContentValues(LeadEvaluation leadEvaluation) {
        return null;
    }

    @Override
    protected LeadEvaluation getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public void insert(LeadEvaluation leadevaluation, Report report) {

    }

    @Override
    public void update(LeadEvaluation leadevaluation) {

    }

    @Override
    public LeadEvaluation select(long id) {
        return null;
    }

    @Override
    public List<LeadEvaluation> selectByReport(Report report) {
        return null;
    }
}
