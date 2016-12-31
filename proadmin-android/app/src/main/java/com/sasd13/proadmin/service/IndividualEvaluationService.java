package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService {

    public interface Callback extends ReadPromise.Callback<IndividualEvaluation>, ManagePromise.Callback {
    }

    private ReadPromise<IndividualEvaluation> readPromise;
    private ManagePromise<IndividualEvaluation> managePromise;

    public IndividualEvaluationService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_INDIVIDUALEVALUATIONS, IndividualEvaluation.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_INDIVIDUALEVALUATIONS);
    }

    public void readByReport(String reportNumber) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.REPORT.getName(), new String[]{reportNumber});
        readPromise.read();
    }

    public void update(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        managePromise.update(getUpdateWrapper(individualEvaluations, individualEvaluationsToUpdate));
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
