package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface ILeadEvaluationService {

    ServiceResult<Void> create(LeadEvaluation leadEvaluation);

    ServiceResult<Void> update(LeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper);
}
