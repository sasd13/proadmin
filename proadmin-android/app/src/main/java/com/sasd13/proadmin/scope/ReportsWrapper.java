package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class ReportsWrapper extends Observable {

    private List<Report> reports;

    public ReportsWrapper() {
        reports = new ArrayList<>();
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;

        setChanged();
        notifyObservers();
    }
}
