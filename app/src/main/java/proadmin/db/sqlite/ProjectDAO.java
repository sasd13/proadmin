package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.Project;
import proadmin.db.ProjectTableAccessor;

public class ProjectDAO extends SQLiteTableDAO<Project> implements ProjectTableAccessor {

    @Override
    protected ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();

        //values.put(PROJECT_ID, project.getId()); //autoincrement
        values.put(PROJECT_CODE, project.getCode());
        values.put(PROJECT_ACADEMICLEVEL, String.valueOf(project.getAcademicLevel()));
        values.put(PROJECT_TITLE, leadEvaluation.getCommunicationMark());
        values.put(LEADEVALUATION_COMMUNICATIONCOMMENT, leadEvaluation.getCommunicationComment());
        values.put(STUDENTS_STUDENT_ID, leadEvaluation.getStudent().getId());

        return values;
    }

    @Override
    protected Project getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public long insert(Project project) {
        return 0;
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
