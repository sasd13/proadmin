package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.Report;
import proadmin.beans.Team;
import proadmin.db.ReportTableAccessor;

public class ReportDAO extends SQLiteTableDAO<Report> implements ReportTableAccessor {

    @Override
    protected ContentValues getContentValues(Report report) {
        return null;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public long insert(Report report) {
        return 0;
    }

    @Override
    public void update(Report report) {

    }

    @Override
    public void delete(Report report) {

    }

    @Override
    public Report select(long id) {
        return null;
    }

    @Override
    public List<Report> selectByTeam(Team team) {
        return null;
    }
}
