package com.sasd13.proadmin.view.gui.form;

import android.content.Context;
import android.view.inputmethod.EditorInfo;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.NumberItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.member.StudentsNumbersBuilder;

import java.util.List;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class LeadEvaluationForm extends Form {

    private static final String[] MARKS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private SpinRadioItemModel modelLeader;
    private NumberItemModel modelPlanningMark, modelCommunicationMark;
    private TextItemModel modelPlanningComment, modelCommunicationComment;
    private List<Student> students;

    public LeadEvaluationForm(Context context) {
        super(context);

        modelLeader = new SpinRadioItemModel();
        modelLeader.setLabel(context.getString(R.string.label_leader));
        holder.add(new RecyclerHolderPair(modelLeader));

        modelPlanningMark = new NumberItemModel();
        modelPlanningMark.setLabel(context.getString(R.string.label_planningmark));
        holder.add(new RecyclerHolderPair(modelPlanningMark));

        modelPlanningComment = new TextItemModel(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        modelPlanningComment.setLabel(context.getString(R.string.label_planningcomment));
        modelPlanningComment.setMultiLine(true);
        holder.add(new RecyclerHolderPair(modelPlanningComment));

        modelCommunicationMark = new NumberItemModel();
        modelCommunicationMark.setLabel(context.getString(R.string.label_communicationmark));
        holder.add(new RecyclerHolderPair(modelCommunicationMark));

        modelCommunicationComment = new TextItemModel(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        modelCommunicationComment.setLabel(context.getString(R.string.label_communicationcomment));
        modelCommunicationComment.setMultiLine(true);
        holder.add(new RecyclerHolderPair(modelCommunicationComment));
    }

    public void bindLeadEvaluation(LeadEvaluation leadEvaluation, List<Student> students) {
        if (leadEvaluation != null) {
            bindLeader(students, leadEvaluation.getStudent());
            bindPlanningMark(MARKS, leadEvaluation.getPlanningMark());
            modelPlanningComment.setValue(leadEvaluation.getPlanningComment());
            bindCommunicationMark(MARKS, leadEvaluation.getCommunicationMark());
            modelCommunicationComment.setValue(leadEvaluation.getCommunicationComment());
        } else {
            bindLeader(students);
            bindPlanningMark(MARKS);
            bindCommunicationMark(MARKS);
        }
    }

    private void bindPlanningMark(String[] values) {
        modelPlanningMark.setItems(values);
    }

    private void bindPlanningMark(String[] values, float mark) {
        bindPlanningMark(values);

        modelPlanningMark.setValue(indexOf(mark, values));
    }

    private int indexOf(float mark, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf((int) mark))) {
                return i;
            }
        }

        return 0;
    }

    private void bindCommunicationMark(String[] values) {
        modelCommunicationMark.setItems(values);
    }

    private void bindCommunicationMark(String[] values, float mark) {
        bindCommunicationMark(values);

        modelCommunicationMark.setValue(indexOf(mark, values));
    }

    private void bindLeader(List<Student> studentsToBind) {
        students = studentsToBind;
        List<String> studentsNumbersWithName = new StudentsNumbersBuilder(students).buildWithName();

        modelLeader.setItems(studentsNumbersWithName.toArray(new String[studentsNumbersWithName.size()]));
    }

    private void bindLeader(List<Student> studentsToBind, Student student) {
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
        if (modelPlanningMark.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_planningmark);
        }

        return Float.valueOf(modelPlanningMark.getStringValue());
    }

    public String getPlanningComment() {
        return modelPlanningComment.getValue() != null ? modelCommunicationComment.getValue().trim() : null;
    }

    public float getCommunicationMark() throws FormException {
        if (modelCommunicationMark.getValue() < 0) {
            throw new FormException(context, R.string.form_leadevaluation_message_error_communicationmark);
        }

        return Float.valueOf(modelCommunicationMark.getStringValue());
    }

    public String getCommunicationComment() {
        return modelCommunicationComment.getValue() != null ? modelCommunicationComment.getValue().trim() : null;
    }
}
