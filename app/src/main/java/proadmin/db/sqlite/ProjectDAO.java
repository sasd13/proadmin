package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.Project;
import proadmin.db.ProjectTableAccessor;

public class ProjectDAO extends SQLiteTableDAO<Project> implements ProjectTableAccessor {

    @Override
    protected ContentValues getContentValues(Project project) {
        return null;
    }

    @Override
    protected Project getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public void insert(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public Project select(long id) {
        return null;
    }

    @Override
    public List<Project> selectByCode(String code) {
        return null;
    }

    @Override
    public List<Project> selectByAcademicLevel(String academicLevel) {
        return null;
    }
}
