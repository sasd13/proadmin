package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService {

    private Promise promiseCreate, promiseUpdate;

    public IndividualEvaluationService() {
        promiseCreate = new Promise("POST", WSResources.URL_WS_INDIVIDUALEVALUATIONS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_INDIVIDUALEVALUATIONS);
    }

    public void update(ICallback callback, List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate, Report report) {
        List<IndividualEvaluation> individualEvaluationsToPost = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluationsToPut = new ArrayList<>();

        boolean toPost;

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            toPost = true;

            for (IndividualEvaluation individualEvaluationToUpdate : individualEvaluationsToUpdate) {
                if (individualEvaluationToUpdate.getStudent().getNumber().equals(individualEvaluation.getStudent().getNumber())) {
                    individualEvaluationsToPut.add(individualEvaluation);
                    toPost = false;

                    break;
                }
            }

            if (toPost) {
                individualEvaluation.setReport(report);
                individualEvaluationsToPost.add(individualEvaluation);
            }
        }

        if (!individualEvaluationsToPut.isEmpty()) {
            promiseUpdate.registerCallback(callback);
            promiseUpdate.execute(getUpdateWrapper(individualEvaluationsToPut, individualEvaluationsToUpdate));
        }

        if (!individualEvaluationsToPost.isEmpty()) {
            promiseCreate.registerCallback(callback);
            promiseCreate.execute(individualEvaluationsToPost.toArray(new IndividualEvaluation[individualEvaluationsToPost.size()]));
        }
    }

    private IndividualEvaluationUpdateWrapper[] getUpdateWrapper(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        List<IndividualEvaluationUpdateWrapper> updateWrappers = new ArrayList<>();

        IndividualEvaluationUpdateWrapper updateWrapper;

        for (IndividualEvaluation individualEvaluationToUpdate : individualEvaluationsToUpdate) {
            updateWrapper = new IndividualEvaluationUpdateWrapper();

            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().getNumber().equals(individualEvaluationToUpdate.getStudent().getNumber())) {
                    individualEvaluation.setReport(individualEvaluationToUpdate.getReport());
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
