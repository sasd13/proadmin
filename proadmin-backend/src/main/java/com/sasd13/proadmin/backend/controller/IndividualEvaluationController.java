package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.service.IIndividualEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.IndividualEvaluationAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.IndividualEvaluationAdapterI2B;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationRequestBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationResponseBean;

@RestController
@RequestMapping("/individualEvaluations")
public class IndividualEvaluationController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationController.class);

	@Autowired
	private IIndividualEvaluationService individualEvaluationService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody IndividualEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] IndividualEvaluation : create");

		try {
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			IndividualEvaluationAdapterI2B adapter = new IndividualEvaluationAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.create(individualEvaluations);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody IndividualEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] IndividualEvaluation : update");

		try {
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			IndividualEvaluationAdapterI2B adapter = new IndividualEvaluationAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.update(individualEvaluations);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody IndividualEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] IndividualEvaluation : delete");

		try {
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			IndividualEvaluationAdapterI2B adapter = new IndividualEvaluationAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.delete(individualEvaluations);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] IndividualEvaluation : search");

		try {
			List<IndividualEvaluation> results = individualEvaluationService.read(searchBean.getCriterias());
			IndividualEvaluationResponseBean responseBean = new IndividualEvaluationResponseBean();
			List<IndividualEvaluationBean> list = new ArrayList<>();
			IndividualEvaluationAdapterB2I adapter = new IndividualEvaluationAdapterB2I();

			for (IndividualEvaluation result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(searchBean, responseBean, list.size());

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
