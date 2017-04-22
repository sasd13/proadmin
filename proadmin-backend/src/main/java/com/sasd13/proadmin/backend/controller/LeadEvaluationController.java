package com.sasd13.proadmin.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.service.ILeadEvaluationService;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

@RestController
@RequestMapping("/leadEvaluation")
public class LeadEvaluationController {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationController.class);

	@Autowired
	private ILeadEvaluationService leadEvaluationService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LeadEvaluation>> get(@RequestParam(value = "reports", required = false) List<String> reports) {
		LOGGER.info("Get");

		try {
			List<LeadEvaluation> leadEvaluations = leadEvaluationService.read(getParameters(reports));

			return new ResponseEntity<List<LeadEvaluation>>(leadEvaluations, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<LeadEvaluation>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> reports) {
		Map<String, String[]> parameters = new HashMap<>();

		if (reports != null) {
			for (String report : reports) {
				parameters.put(EnumParameter.NUMBER.getName(), new String[] { report });
			}
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody LeadEvaluation leadEvaluation) {
		LOGGER.info("Post");

		try {
			leadEvaluationService.create(leadEvaluation);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<LeadEvaluationUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			leadEvaluationService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<LeadEvaluation> leadEvaluations) {
		LOGGER.info("Delete");

		try {
			leadEvaluationService.delete(leadEvaluations);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
