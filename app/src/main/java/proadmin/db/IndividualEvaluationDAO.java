package proadmin.db;

import java.util.List;

import proadmin.bean.running.IndividualEvaluation;

public interface IndividualEvaluationDAO {

    String INDIVIDUALEVALUATION_TABLE_NAME = "individualevaluations";

    String INDIVIDUALEVALUATION_ID = "individualevaluation_id";
    String INDIVIDUALEVALUATION_MARK = "individualevaluation_mark";
    String STUDENTS_STUDENT_ID = "students_student_id";
    String REPORTS_REPORT_ID = "reports_report_id";

    long insert(IndividualEvaluation individualEvaluation);

    void update(IndividualEvaluation individualEvaluation);

    void deleteByReport(long reportId);

    IndividualEvaluation select(long id);

    List<IndividualEvaluation> selectByReport(long reportId);
}
