package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IIndividualEvaluationService {

    void create(List<IndividualEvaluation> individualEvaluations);

    void update(List<IndividualEvaluationUpdateWrapper> individualEvaluationUpdateWrappers);
}
