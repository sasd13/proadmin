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

import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.service.ILeadEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.LeadEvaluationAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.LeadEvaluationAdapterI2B;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

@RestController
@RequestMapping("/leadEvaluations")
public class LeadEvaluationController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationController.class);

	@Autowired
	private ILeadEvaluationService leadEvaluationService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody LeadEvaluationBean leadEvaluationBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : create");

		try {
			leadEvaluationService.create(new LeadEvaluationAdapterI2B().adapt(leadEvaluationBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody LeadEvaluationBean leadEvaluationBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : update");

		try {
			leadEvaluationService.update(new LeadEvaluationAdapterI2B().adapt(leadEvaluationBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody LeadEvaluationBean leadEvaluationBean) {
		LOGGER.info("[Proadmin-Backend] LeadEvaluation : delete");

		try {
			leadEvaluationService.delete(new LeadEvaluationAdapterI2B().adapt(leadEvaluationBean));

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
			List<LeadEvaluation> results = leadEvaluationService.read(searchBean.getCriterias());
			ResponseBean responseBean = new ResponseBean();
			List<LeadEvaluationBean> list = new ArrayList<>();
			LeadEvaluationAdapterB2I adapter = new LeadEvaluationAdapterB2I();

			for (LeadEvaluation result : results) {
				list.add(adapter.adapt(result));
			}

			addHeaders(searchBean, responseBean);
			responseBean.getContext().setPaginationTotalItems(String.valueOf(list.size()));
			responseBean.setData(list);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
