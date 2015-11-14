package proadmin.data.dao.accessor;

import proadmin.beans.Report;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ReportAccessor {

    void insertReport(Report report);

    void updateReport(Report report);

    void deleteReport(String reportId);

    Report selectReport(String reportId);

    ListReports selectReportsOfSquad(String squadId);
}
