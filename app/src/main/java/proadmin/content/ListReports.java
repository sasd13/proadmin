package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Report get(String reportId) {
        for (Report report : this.listReports) {
            if (report.getId().compareTo(reportId) == 0) {
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
