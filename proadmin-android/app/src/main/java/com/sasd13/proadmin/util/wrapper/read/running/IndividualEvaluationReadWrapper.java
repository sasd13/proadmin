package com.sasd13.proadmin.util.wrapper.read.running;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class IndividualEvaluationReadWrapper implements IReadWrapper<IndividualEvaluation> {

    private List<IndividualEvaluation> individualEvaluations;

    public IndividualEvaluationReadWrapper(List<IndividualEvaluation> individualEvaluations) {
        this.individualEvaluations = individualEvaluations;
    }

    @Override
    public List<IndividualEvaluation> getWrapped() {
        return individualEvaluations;
    }
}
