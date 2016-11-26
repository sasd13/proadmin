package com.sasd13.proadmin.ws.wrapper.running;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 11/11/2016.
 */

public class LeadEvaluationReadWrapper implements IReadWrapper<List<LeadEvaluation>> {

    private List<LeadEvaluation> leadEvaluations;

    public LeadEvaluationReadWrapper(List<LeadEvaluation> leadEvaluations) {
        this.leadEvaluations = leadEvaluations;
    }

    @Override
    public List<LeadEvaluation> getWrapped() {
        return leadEvaluations;
    }
}
