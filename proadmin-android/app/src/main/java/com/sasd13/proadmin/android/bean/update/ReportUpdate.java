package com.sasd13.proadmin.android.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.android.bean.Report;

public class ReportUpdate implements IUpdateWrapper<Report> {

    private Report report;
    private String number;

    @Override
    public Report getWrapped() {
        return report;
    }

    @Override
    public void setWrapped(Report report) {
        this.report = report;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
