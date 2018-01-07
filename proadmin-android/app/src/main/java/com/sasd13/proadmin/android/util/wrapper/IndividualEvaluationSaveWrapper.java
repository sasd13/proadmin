package com.sasd13.proadmin.android.util.wrapper;

import com.sasd13.proadmin.android.model.IndividualEvaluation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class IndividualEvaluationSaveWrapper {

    private List<IndividualEvaluation> individualEvaluationsToCreate;
    private List<IndividualEvaluation> individualEvaluationsToUpdate;

    public IndividualEvaluationSaveWrapper() {
        individualEvaluationsToCreate = new ArrayList<>();
        individualEvaluationsToUpdate = new ArrayList<>();
    }

    public List<IndividualEvaluation> getIndividualEvaluationsToCreate() {
        return individualEvaluationsToCreate;
    }

    public void setIndividualEvaluationsToCreate(List<IndividualEvaluation> individualEvaluationsToCreate) {
        this.individualEvaluationsToCreate = individualEvaluationsToCreate;
    }

    public List<IndividualEvaluation> getIndividualEvaluationsToUpdate() {
        return individualEvaluationsToUpdate;
    }

    public void setIndividualEvaluationsToUpdate(List<IndividualEvaluation> individualEvaluationsToUpdate) {
        this.individualEvaluationsToUpdate = individualEvaluationsToUpdate;
    }
}
