package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.Id;
import proadmin.content.id.ReportId;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListReports implements Iterable {

    private List<Report> listReports;

    public ListReports() {
        this.listReports = new ArrayList<>();
    }

    public boolean add(Report report) {
        return this.listReports.add(report);
    }

    public boolean remove(Report report) {
        return this.listReports.remove(report);
    }

    public Report get(int index) {
        return this.listReports.get(index);
    }

    public Report get(ReportId reportId) {
        for (Report report : this.listReports) {
            if (report.getId().equals(reportId)) {
                return report;
            }
        }

        return null;
    }

    public int size() {
        return this.listReports.size();
    }

    @Override
    public Iterator iterator() {
        return this.listReports.iterator();
    }
}
