package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.member.StudentsNumberBuilder;
import com.sasd13.proadmin.util.builder.running.StringMarksBuilder;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class LeadEvaluationForm extends Form {

    private SpinRadioItemModel modelLeader, modelPlanningMark, modelCommunicationMark;
    private TextItemModel modelPlanningComment, modelCommunicationComment;
    private List<Student> students;
    private List<Float> planningMarks, communicationMarks;

    public LeadEvaluationForm(Context context) {
        super(context);

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getResources().getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelPlanningMark = new SpinRadioItemModel();
        modelPlanningMark.setLabel(context.getResources().getString(R.string.label_planningmark));
        holder.add(new RecyclerHolderPair(modelPlanningMark));

        modelPlanningComment = new TextItemModel();
        modelPlanningComment.setLabel(context.getResources().getString(R.string.label_planningcomment));
        holder.add(new RecyclerHolderPair(modelPlanningComment));

        modelCommunicationMark = new SpinRadioItemModel();
        modelCommunicationMark.setLabel(context.getResources().getString(R.string.label_communicationmark));
        holder.add(new RecyclerHolderPair(modelCommunicationMark));

        modelCommunicationComment = new TextItemModel();
        modelCommunicationComment.setLabel(context.getResources().getString(R.string.label_communicationcomment));
        holder.add(new RecyclerHolderPair(modelCommunicationComment));
    }

    public void bindLeadEvaluation(LeadEvaluation leadEvaluation) {
        modelPlanningComment.setValue(leadEvaluation.getPlanningComment());
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

    public void bindPlanningMark(List<Float> marksToBind) {
        planningMarks = marksToBind;
        List<String> marksInString = new StringMarksBuilder(planningMarks).build();

        modelPlanningMark.setItems(marksInString.toArray(new String[marksInString.size()]));
    }

    public void bindPlanningMark(List<Float> marksToBind, float planningMark) {
        bindPlanningMark(marksToBind);

        modelPlanningMark.setValue(Finder.indexOfMark(planningMark, marksToBind));
    }

    public void bindCommunicationMark(List<Float> marksToBind) {
        communicationMarks = marksToBind;
        List<String> marksInString = new StringMarksBuilder(communicationMarks).build();

        modelCommunicationMark.setItems(marksInString.toArray(new String[marksInString.size()]));
    }

    public void bindCommunicationMark(List<Float> marksToBind, float communicationMark) {
        bindCommunicationMark(marksToBind);

        modelCommunicationMark.setValue(Finder.indexOfMark(communicationMark, marksToBind));
    }

    public Student getLeader() throws FormException {
        if (modelLeader.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_leader);
        }

        return students.get(modelLeader.getValue());
    }

    public float getPlanningMark() throws FormException {
        if (modelPlanningMark.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_planningmark);
        }

        return planningMarks.get(modelPlanningMark.getValue());
    }

    public String getPlanningComment() {
        return modelPlanningComment.getValue().trim();
    }

    public float getCommunicationMark() throws FormException {
        if (modelCommunicationMark.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_communicationmark);
        }

        return communicationMarks.get(modelCommunicationMark.getValue());
    }

    public String getCommunicationComment() {
        return modelCommunicationComment.getValue().trim();
    }
}
