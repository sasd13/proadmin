package com.sasd13.proadmin.gui.form;

import android.content.Context;
import android.view.inputmethod.EditorInfo;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.Constants;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class IndividualEvaluationsForm extends Form {

    private DecimalFormat formatter;
    private Map<String, TextItemModel> modelsMarks;
    private List<IndividualEvaluation> individualEvaluations;

    public IndividualEvaluationsForm(Context context) {
        super(context);

        modelsMarks = new HashMap<>();
        formatter = new DecimalFormat(Constants.DEFAULT_MARK_PATTERN);
    }

    public void bindIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        holder.clear();

        this.individualEvaluations = individualEvaluations;

        TextItemModel modelMark;

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            modelMark = new TextItemModel(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL, formatter);
            modelMark.setLabel(individualEvaluation.getStudent().getFullName());
            modelMark.setValue(formatter.format(individualEvaluation.getMark()));

            modelsMarks.put(individualEvaluation.getStudent().getNumber(), modelMark);
            holder.add(new RecyclerHolderPair(modelMark));
        }
    }

    public List<IndividualEvaluation> getIndividualEvaluations() throws IndividualEvaluationsFormException {
        List<IndividualEvaluation> individualEvaluationsFromForm = new ArrayList<>();

        IndividualEvaluation individualEvaluationFromForm;

        for (Map.Entry<String, TextItemModel> entry : modelsMarks.entrySet()) {
            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().getNumber().equals(entry.getKey())) {
                    try {
                        individualEvaluationFromForm = new IndividualEvaluation();

                        individualEvaluationFromForm.setReport(individualEvaluation.getReport());
                        individualEvaluationFromForm.setStudent(individualEvaluation.getStudent());
                        individualEvaluationFromForm.setMark(formatter.parse(entry.getValue().getValue()).floatValue());

                        individualEvaluationsFromForm.add(individualEvaluationFromForm);
                    } catch (ParseException e) {
                        throw new IndividualEvaluationsFormException(
                                context,
                                R.string.form_individualevaluation_message_error_mark,
                                entry.getKey());
                    }
                }
            }
        }

        return individualEvaluationsFromForm;
    }
}
