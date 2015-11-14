package proadmin.db;

import java.util.List;

import proadmin.beans.IndividualEvaluation;
import proadmin.beans.Report;
import proadmin.beans.Student;

public interface IndividualEvaluationTableAccessor {

    String INDIVIDUALEVALUATION_TABLE_NAME = "individualevaluations";

    String INDIVIDUALEVALUATION_ID = "individualevaluation_id";
    String INDIVIDUALEVALUATION_MARK = "individualevaluation_mark";
    String STUDENTS_STUDENT_ID = "students_student_id";
    String REPORTS_REPORT_ID = "reports_report_id";

    long insert(IndividualEvaluation individualevaluation, Report report);

    void update(IndividualEvaluation individualevaluation);

    IndividualEvaluation select(long id);

    List<IndividualEvaluation> selectByReport(Report report);
}
