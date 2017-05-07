package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;

/**
 * Created by ssaidali2 on 02/12/2016.
 */
public class NewLeadEvaluationBuilder implements IBuilder<LeadEvaluation> {

    private Report report;

    public NewLeadEvaluationBuilder(Report report) {
        this.report = report;
    }

    @Override
    public LeadEvaluation build() {
        LeadEvaluation leadEvaluation = new LeadEvaluation();

        leadEvaluation.setReport(report);

        return leadEvaluation;
    }
}
