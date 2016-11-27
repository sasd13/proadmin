package com.sasd13.proadmin.util.builder.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.ReportForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class ReportFromFormBuilder implements IBuilderFromForm<Report> {

    private ReportForm reportForm;

    public ReportFromFormBuilder(ReportForm reportForm) {
        this.reportForm = reportForm;
    }

    @Override
    public Report build() throws FormException {
        Report reportFromForm = new Report();

        reportFromForm.setNumber(reportForm.getNumber());
        reportFromForm.setDateMeeting(reportForm.getDateMeeting());
        reportFromForm.setSession(reportForm.getSession());
        reportFromForm.setComment(reportForm.getComment());

        return reportFromForm;
    }
}
