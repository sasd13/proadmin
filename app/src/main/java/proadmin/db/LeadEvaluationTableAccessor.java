package proadmin.db;

import java.util.List;

import proadmin.beans.LeadEvaluation;
import proadmin.beans.Report;
import proadmin.beans.Student;

public interface LeadEvaluationTableAccessor {

    String LEADEVALUATION_TABLE_NAME = "leadevaluations";

    String LEADEVALUATION_ID = "leadevaluation_id";
    String LEADEVALUATION_PLANNINGMARK = "leadevaluation_planningmark";
    String LEADEVALUATION_PLANNINGCOMMENT = "leadevaluation_planningcomment";
    String LEADEVALUATION_COMMUNICATIONMARK = "leadevaluation_communicationmark";
    String LEADEVALUATION_COMMUNICATIONCOMMENT = "leadevaluation_communicationcomment";
    String STUDENTS_STUDENT_ID = "students_student_id";
    String REPORTS_REPORT_ID = "reports_report_id";

    void insert(LeadEvaluation leadevaluation, Report report);

    void update(LeadEvaluation leadevaluation);

    LeadEvaluation select(long id);

    List<LeadEvaluation> selectByReport(Report report);
}
