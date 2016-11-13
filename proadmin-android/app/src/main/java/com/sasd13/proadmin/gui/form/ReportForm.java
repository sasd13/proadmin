package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.sql.Timestamp;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class ReportForm extends Form {

    private boolean inModeEdit;
    private TextItemModel modelNumber, modelSession, modelComment;
    private DateItemModel modelDateMeeting;

    public ReportForm(Context context, boolean inModeEdit) {
        super(context);

        this.inModeEdit = inModeEdit;

        if (inModeEdit) {
            modelNumber = new TextItemModel();
            modelNumber.setReadOnly(true);
            modelNumber.setLabel(context.getResources().getString(R.string.label_number));
            modelNumber.setHint(context.getResources().getString(R.string.label_number));
            holder.add(new RecyclerHolderPair(modelNumber));
        }

        modelSession = new TextItemModel();
        modelSession.setLabel(context.getResources().getString(R.string.label_session));
        holder.add(new RecyclerHolderPair(modelSession));

        modelDateMeeting = new DateItemModel();
        modelDateMeeting.setLabel(context.getResources().getString(R.string.label_datemeeting));
        holder.add(new RecyclerHolderPair(modelDateMeeting));

        modelComment = new TextItemModel();
        modelComment.setLabel(context.getResources().getString(R.string.label_comment));
        holder.add(new RecyclerHolderPair(modelComment));
    }

    public void bindReport(Report report) {
        if (inModeEdit) {
            modelNumber.setValue(String.valueOf(report.getNumber()));
        }

        modelSession.setValue(String.valueOf(report.getSession()));
        modelDateMeeting.setValue(new LocalDate(report.getDateMeeting()));
        modelComment.setValue(report.getComment());
    }

    public String getNumber() throws FormException {
        if (!inModeEdit) {
            throw new FormException(context, R.string.form_message_error);
        }

        if (StringUtils.isBlank(modelNumber.getValue())) {
            throw new FormException(context, R.string.form_report_message_error_number);
        }

        return modelNumber.getValue();
    }

    public int getSession() throws FormException {
        if (!StringUtils.isNumeric(modelSession.getValue())) {
            throw new FormException(context, R.string.form_report_message_error_session);
        }

        return Integer.parseInt(modelSession.getValue());
    }

    public Timestamp getDateMeeting() throws FormException {
        return new Timestamp(modelDateMeeting.getValue().toDate().getTime());
    }

    public String getComment() {
        return modelComment.getValue();
    }
}