package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.model.LeadEvaluation;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface ILeadEvaluationService {

    ServiceResult<Void> create(LeadEvaluation leadEvaluation);

    ServiceResult<Void> update(LeadEvaluation leadEvaluation);
}
