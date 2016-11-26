package com.sasd13.proadmin.ws.wrapper.running;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class ReportReadWrapper implements IReadWrapper<List<Report>> {

    private List<Report> reports;

    public ReportReadWrapper(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public List<Report> getWrapped() {
        return reports;
    }
}
