package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.ReportId;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListReports implements Iterable {

    private List<Report> list;

    public ListReports() {
        this.list = new ArrayList<>();
    }

    public boolean add(Report report) {
        return this.list.add(report);
    }

    public boolean remove(Report report) {
        return this.list.remove(report);
    }

    public Report get(int index) {
        return this.list.get(index);
    }

    public Report get(ReportId reportId) {
        for (Report report : this.list) {
            if (report.getId().equals(reportId)) {
                return report;
            }
        }

        return null;
    }

    public int size() {
        return this.list.size();
    }

    public void clear() {
        this.list.clear();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
