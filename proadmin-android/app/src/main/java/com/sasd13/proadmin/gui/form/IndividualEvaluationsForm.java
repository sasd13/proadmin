package com.sasd13.proadmin.gui.form;

import android.content.Context;

import com.sasd13.androidex.gui.form.Form;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.Finder;
import com.sasd13.proadmin.util.builder.running.StringMarksBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class IndividualEvaluationsForm extends Form {

    private Map<String, SpinRadioItemModel> modelsMarks;
    private List<Float> marks;

    public IndividualEvaluationsForm(Context context) {
        super(context);

        modelsMarks = new HashMap<>();
    }

    public void bindMarks(List<Float> marksToBind, List<IndividualEvaluation> individualEvaluations) {
        marks = marksToBind;
        List<String> marksInString = new StringMarksBuilder(marksToBind).build();
        String[] marksToModel = marksInString.toArray(new String[marksInString.size()]);

        SpinRadioItemModel modelMark;

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            modelMark = new SpinRadioItemModel();
            modelMark.setLabel(individualEvaluation.getStudent().getFullName());
            modelMark.setItems(marksToModel);
            modelMark.setValue(Finder.indexOfMark(individualEvaluation.getMark(), marksToBind));

            modelsMarks.put(individualEvaluation.getStudent().getNumber(), modelMark);
            holder.add(new RecyclerHolderPair(modelMark));
        }
    }

    public Map<String, Float> getMarks() throws IndividualEvaluationsFormException {
        Map<String, Float> studentsMarks = new HashMap<>();

        for (Map.Entry<String, SpinRadioItemModel> entry : modelsMarks.entrySet()) {
            int indexOfMark = entry.getValue().getValue();

            if (indexOfMark < 0) {
                throw new IndividualEvaluationsFormException(
                        context,
                        R.string.form_individualevaluation_message_error_mark,
                        entry.getKey());
            }

            studentsMarks.put(entry.getKey(), marks.get(indexOfMark));
        }

        return studentsMarks;
    }
}
