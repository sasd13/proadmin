package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.Date;

/**
 * Created by ssaidali2 on 02/12/2016.
 */
public class DefaultReportBuilder implements IBuilder<Report> {

    private RunningTeam runningTeam;

    public DefaultReportBuilder() {
    }

    public DefaultReportBuilder(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public Report build() {
        Report report = new Report();

        report.setDateMeeting(new Date());
        report.setRunningTeam(runningTeam);

        return report;
    }
}
