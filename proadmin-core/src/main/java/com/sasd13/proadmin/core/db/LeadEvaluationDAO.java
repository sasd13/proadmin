package com.sasd13.proadmin.core.db;

import com.sasd13.proadmin.core.bean.running.LeadEvaluation;

public interface LeadEvaluationDAO {

    String LEADEVALUATION_TABLE_NAME = "leadevaluations";

    String LEADEVALUATION_ID = "leadevaluation_id";
    String LEADEVALUATION_PLANNINGMARK = "leadevaluation_planningmark";
    String LEADEVALUATION_PLANNINGCOMMENT = "leadevaluation_planningcomment";
    String LEADEVALUATION_COMMUNICATIONMARK = "leadevaluation_communicationmark";
    String LEADEVALUATION_COMMUNICATIONCOMMENT = "leadevaluation_communicationcomment";
    String STUDENTS_STUDENT_ID = "students_student_id";
    String REPORTS_REPORT_ID = "reports_report_id";

    long insert(LeadEvaluation leadEvaluation);

    void update(LeadEvaluation leadEvaluation);

    LeadEvaluation select(long id);

    LeadEvaluation selectByReport(long reportId);
}
