package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.model.IndividualEvaluation;

import java.util.List;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IIndividualEvaluationService {

    ServiceResult<Void> create(List<IndividualEvaluation> individualEvaluations);

    ServiceResult<Void> update(List<IndividualEvaluation> individualEvaluations);
}
