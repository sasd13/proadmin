package com.sasd13.proadmin.android.util.adapter.itf2bean.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReportAdapterI2B implements IAdapter<ReportBean, Report> {

    private static final Logger LOGGER = Logger.getLogger(ReportAdapterI2B.class);

    @Override
    public Report adapt(ReportBean s) {
        Report t = new Report();

        t.setId(Long.valueOf(s.getId().getId()));

        try {
            t.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(s.getCoreInfo().getDateMeeting()));
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new RuntimeException("ReportAdapterI2B : error parsing date " + s.getCoreInfo().getDateMeeting());
        }

        t.setNumber(s.getCoreInfo().getNumber());
        t.setSession(s.getCoreInfo().getSession());
        t.setComment(s.getCoreInfo().getComment());

        RunningTeam runningTeam = new RunningTeam();
        runningTeam.setId(Long.valueOf(s.getLinkedRunningTeam().getId()));
        t.setRunningTeam(runningTeam);

        return t;
    }
}
