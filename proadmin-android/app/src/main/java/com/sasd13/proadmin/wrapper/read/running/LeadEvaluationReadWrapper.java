package com.sasd13.proadmin.wrapper.read.running;

import com.sasd13.proadmin.bean.running.LeadEvaluation;

import java.util.List;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class LeadEvaluationReadWrapper implements ILeadEvaluationReadWrapper {

    private List<LeadEvaluation> leadEvaluations;

    public LeadEvaluationReadWrapper(List<LeadEvaluation> leadEvaluations) {
        this.leadEvaluations = leadEvaluations;
    }

    @Override
    public List<LeadEvaluation> getWrapped() {
        return leadEvaluations;
    }
}
