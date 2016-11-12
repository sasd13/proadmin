package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.sql.Timestamp;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class LeadEvaluationForm extends Form {

    private SpinRadioItemModel modelLeader, modelPlanningMark, modelCommunicationMark;
    private TextItemModel modelPlanningComment, modelCommunicationComment;

    public LeadEvaluationForm(Context context) {
        super(context);

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelPlanningMark = new SpinRadioItemModel();
        modelPlanningMark.setLabel(context.getResources().getString(R.string.label_planningmark));
        holder.add(new RecyclerHolderPair(modelPlanningMark));

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));
    }

    public void bindReport(Report report) {
        modelNumber.setValue(String.valueOf(report.getNumber()));
        modelSession.setValue(String.valueOf(report.getSession()));
        modelDateMeeting.setValue(new LocalDate(report.getDateMeeting()));
        modelComment.setValue(report.getComment());
    }

    public String getNumber() throws FormException {
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

    public String getComment() throws FormException {
        if (StringUtils.isBlank(modelComment.getValue())) {
            throw new FormException(context, R.string.form_report_message_error_comment);
        }

        return modelComment.getValue();
    }
}
