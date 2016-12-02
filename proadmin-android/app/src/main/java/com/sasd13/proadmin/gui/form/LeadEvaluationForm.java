package com.sasd13.proadmin.gui.form;

import android.content.Context;
import android.view.inputmethod.EditorInfo;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.member.StudentsNumberBuilder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class LeadEvaluationForm extends Form {

    private SpinRadioItemModel modelLeader;
    private TextItemModel modelPlanningMark, modelPlanningComment, modelCommunicationMark, modelCommunicationComment;
    private List<Student> students;
    private DecimalFormat formatter;

    public LeadEvaluationForm(Context context) {
        super(context);

        formatter = new DecimalFormat(Constants.DEFAULT_MARK_PATTERN);

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelPlanningMark = new TextItemModel(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL, formatter);
        modelPlanningMark.setLabel(context.getResources().getString(R.string.label_planningmark));
        holder.add(new RecyclerHolderPair(modelPlanningMark));

        modelPlanningComment = new TextItemModel(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        modelPlanningComment.setLabel(context.getResources().getString(R.string.label_planningcomment));
        modelPlanningComment.setMultiLine(true);
        holder.add(new RecyclerHolderPair(modelPlanningComment));

        modelCommunicationMark = new TextItemModel(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL, formatter);
        modelCommunicationMark.setLabel(context.getResources().getString(R.string.label_communicationmark));
        holder.add(new RecyclerHolderPair(modelCommunicationMark));

        modelCommunicationComment = new TextItemModel(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        modelCommunicationComment.setLabel(context.getResources().getString(R.string.label_communicationcomment));
        modelCommunicationComment.setMultiLine(true);
        holder.add(new RecyclerHolderPair(modelCommunicationComment));
    }

    public void bindLeadEvaluation(LeadEvaluation leadEvaluation) {
        modelPlanningMark.setValue(formatter.format(leadEvaluation.getPlanningMark()));
        modelPlanningComment.setValue(leadEvaluation.getPlanningComment());
        modelCommunicationMark.setValue(formatter.format(leadEvaluation.getCommunicationMark()));
        modelCommunicationComment.setValue(leadEvaluation.getCommunicationComment());
    }

    public void bindLeader(List<Student> studentsToBind) {
        students = studentsToBind;
        List<String> studentsNumbersWithName = new StudentsNumberBuilder(students).buildWithName();

        modelLeader.setItems(studentsNumbersWithName.toArray(new String[studentsNumbersWithName.size()]));
    }

    public void bindLeader(List<Student> studentsToBind, Student student) {
        bindLeader(studentsToBind);

        modelLeader.setValue(Finder.indexOfStudent(student.getNumber(), studentsToBind));
    }

    public Student getLeader() throws FormException {
        if (modelLeader.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_leader);
        }

        return students.get(modelLeader.getValue());
    }

    public float getPlanningMark() throws FormException {
        try {
            return formatter.parse(modelPlanningMark.getValue()).floatValue();
        } catch (ParseException e) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_planningmark);
        }
    }

    public String getPlanningComment() {
        return modelPlanningComment.getValue() != null ? modelCommunicationComment.getValue().trim() : null;
    }

    public float getCommunicationMark() throws FormException {
        try {
            return formatter.parse(modelCommunicationMark.getValue()).floatValue();
        } catch (ParseException e) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_communicationmark);
        }
    }

    public String getCommunicationComment() {
        return modelCommunicationComment.getValue() != null ? modelCommunicationComment.getValue().trim() : null;
    }
}
