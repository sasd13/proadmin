package com.sasd13.proadmin.android.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.NumberItemModel;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.util.IndexFinder;
import com.sasd13.proadmin.android.util.builder.LabelBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class IndividualEvaluationsForm extends Form {

    private final String[] MARKS;
    private Map<IndividualEvaluation, NumberItemModel> modelsMarks;

    public IndividualEvaluationsForm(Context context) {
        super(context);

        MARKS = context.getResources().getStringArray(R.array.marks);
        modelsMarks = new HashMap<>();
    }

    public void bindIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        holder.clear();

        NumberItemModel modelMark;

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            modelMark = new NumberItemModel();
            modelMark.setLabel(LabelBuilder.studentIntermediaryWithShortenFullName(individualEvaluation.getStudent()));
            modelMark.setItems(MARKS);
            modelMark.setValue(IndexFinder.indexOf(individualEvaluation.getMark(), MARKS));

            modelsMarks.put(individualEvaluation, modelMark);
            holder.add(new RecyclerHolderPair(modelMark));
        }
    }

    public Map<IndividualEvaluation, Float> getMarks() throws IndividualEvaluationsFormException {
        Map<IndividualEvaluation, Float> marks = new HashMap<>();

        for (Map.Entry<IndividualEvaluation, NumberItemModel> entry : modelsMarks.entrySet()) {
            if (entry.getValue().getValue() < 0) {
                throw new IndividualEvaluationsFormException(context, R.string.form_individualevaluation_message_error_mark);
            }

            marks.put(entry.getKey(), Float.valueOf(MARKS[entry.getValue().getValue()]));
        }

        return marks;
    }
}
