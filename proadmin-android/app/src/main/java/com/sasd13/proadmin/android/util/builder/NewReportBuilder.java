package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.RunningTeam;

import java.util.Date;

/**
 * Created by ssaidali2 on 02/12/2016.
 */
public class NewReportBuilder implements IBuilder<Report> {

    private RunningTeam runningTeam;

    public NewReportBuilder() {
    }

    public NewReportBuilder(RunningTeam runningTeam) {
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
