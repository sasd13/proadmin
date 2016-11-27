package com.sasd13.proadmin.util.builder.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class LeadEvaluationFromFormBuilder implements IBuilderFromForm<LeadEvaluation> {

    private LeadEvaluationForm leadEvaluationForm;

    public LeadEvaluationFromFormBuilder(LeadEvaluationForm leadEvaluationForm) {
        this.leadEvaluationForm = leadEvaluationForm;
    }

    @Override
    public LeadEvaluation build() throws FormException {
        LeadEvaluation leadEvaluationFromForm = new LeadEvaluation();

        leadEvaluationFromForm.setPlanningMark(leadEvaluationForm.getPlanningMark());
        leadEvaluationFromForm.setPlanningComment(leadEvaluationForm.getPlanningComment());
        leadEvaluationFromForm.setCommunicationMark(leadEvaluationForm.getCommunicationMark());
        leadEvaluationFromForm.setCommunicationComment(leadEvaluationForm.getCommunicationComment());
        leadEvaluationFromForm.setStudent(leadEvaluationForm.getLeader());

        return leadEvaluationFromForm;
    }
}
