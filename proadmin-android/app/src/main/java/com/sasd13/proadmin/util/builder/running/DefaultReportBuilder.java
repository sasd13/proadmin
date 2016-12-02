package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Constants;

import java.sql.Timestamp;

/**
 * Created by ssaidali2 on 02/12/2016.
 */
public class DefaultReportBuilder implements IBuilder<Report> {

    @Override
    public Report build() {
        Report report = new Report();

        report.setNumber(Constants.DEFAULT_REPORT_NUMBER);
        report.setDateMeeting(new Timestamp(System.currentTimeMillis()));

        return report;
    }
}
