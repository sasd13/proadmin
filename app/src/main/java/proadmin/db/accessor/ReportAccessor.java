package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.ListReports;
import proadmin.content.Report;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ReportAccessor {

    boolean insertReport(Report report, Id squadId);

    void updateReport(Report report, Id squadId);

    void deleteReport(Id reportId);

    Report selectReport(Id reportId);

    ListReports selectReports(Id squadId);
}
