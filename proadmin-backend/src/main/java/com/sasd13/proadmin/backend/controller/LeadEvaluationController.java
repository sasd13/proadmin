package com.sasd13.proadmin.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.service.ILeadEvaluationService;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationRequestBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationResponseBean;

@RestController
@RequestMapping("/leadEvaluations")
public class LeadEvaluationController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationController.class);

	@Autowired
	private ILeadEvaluationService leadEvaluationService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody LeadEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : create");

		try {
			leadEvaluationService.create(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody LeadEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : update");

		try {
			leadEvaluationService.update(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody LeadEvaluationRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : delete");

		try {
			leadEvaluationService.delete(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : search");

		try {
			List<LeadEvaluationBean> results = leadEvaluationService.read(searchBean.getCriterias());
			LeadEvaluationResponseBean responseBean = new LeadEvaluationResponseBean();

			responseBean.setData(results);
			addHeaders(responseBean, results.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
