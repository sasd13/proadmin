package com.sasd13.proadmin.view.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.NumberItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class IndividualEvaluationsForm extends Form {

    private static final String[] MARKS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private Map<String, NumberItemModel> modelsMarks;
    private List<Student> students;

    public IndividualEvaluationsForm(Context context) {
        super(context);

        modelsMarks = new HashMap<>();
    }

    public void bindIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<Student> students) {
        holder.clear();

        this.students = students;

        NumberItemModel modelMark;

        for (Student student : students) {
            modelMark = new NumberItemModel();
            modelMark.setLabel(student.getFullName());
            modelMark.setItems(MARKS);

            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().getNumber().equals(student.getNumber())) {
                    modelMark.setValue(indexOf(individualEvaluation.getMark(), MARKS));
                }
            }

            modelsMarks.put(student.getNumber(), modelMark);
            holder.add(new RecyclerHolderPair(modelMark));
        }
    }

    private int indexOf(float mark, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf((int) mark))) {
                return i;
            }
        }

        return 0;
    }

    public List<IndividualEvaluation> getIndividualEvaluations(Report report) throws IndividualEvaluationsFormException {
        List<IndividualEvaluation> individualEvaluationsFromForm = new ArrayList<>();

        IndividualEvaluation individualEvaluationFromForm;

        for (Map.Entry<String, NumberItemModel> entry : modelsMarks.entrySet()) {
            for (Student student : students) {
                if (student.getNumber().equals(entry.getKey())) {
                    if (entry.getValue().getValue() < 0) {
                        throw new IndividualEvaluationsFormException(context, R.string.form_individualevaluation_message_error_mark);
                    }

                    individualEvaluationFromForm = new IndividualEvaluation();

                    individualEvaluationFromForm.setReport(report);
                    individualEvaluationFromForm.setStudent(student);
                    individualEvaluationFromForm.setMark(Float.valueOf(MARKS[entry.getValue().getValue()]));

                    individualEvaluationsFromForm.add(individualEvaluationFromForm);

                    break;
                }
            }
        }

        return individualEvaluationsFromForm;
    }
}
