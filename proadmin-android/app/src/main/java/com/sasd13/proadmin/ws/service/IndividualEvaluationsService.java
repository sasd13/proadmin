package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationsService {

    private ManageService<IndividualEvaluation> manageService;

    public IndividualEvaluationsService(ManageService.Caller caller) {
        manageService = new ManageService<>(caller, WSResources.URL_WS_INDIVIDUALEVALUATIONS);
    }

    public void update(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        manageService.update(getUpdateWrapper(individualEvaluations, individualEvaluationsToUpdate));
    }

    private IndividualEvaluationUpdateWrapper[] getUpdateWrapper(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        List<IndividualEvaluationUpdateWrapper> updateWrappers = new ArrayList<>();

        IndividualEvaluationUpdateWrapper updateWrapper;

        for (IndividualEvaluation individualEvaluationToUpdate : individualEvaluationsToUpdate) {
            updateWrapper = new IndividualEvaluationUpdateWrapper();

            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().getNumber().equals(individualEvaluationToUpdate.getStudent().getNumber())) {
                    updateWrapper.setWrapped(individualEvaluation);
                    updateWrapper.setReportNumber(individualEvaluationToUpdate.getReport().getNumber());
                    updateWrapper.setStudentNumber(individualEvaluationToUpdate.getStudent().getNumber());
                }
            }

            updateWrappers.add(updateWrapper);
        }

        return updateWrappers.toArray(new IndividualEvaluationUpdateWrapper[updateWrappers.size()]);
    }
}
