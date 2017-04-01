package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface ILeadEvaluationService {

    void create(List<LeadEvaluation> leadEvaluations);

    void update(List<LeadEvaluationUpdateWrapper> leadEvaluationUpdateWrappers);
}
