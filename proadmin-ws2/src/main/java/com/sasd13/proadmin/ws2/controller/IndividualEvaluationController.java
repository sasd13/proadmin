package com.sasd13.proadmin.ws2.controller;

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

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IIndividualEvaluationService;

@RestController
@RequestMapping("/individualEvaluation")
public class IndividualEvaluationController {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationController.class);

	@Autowired
	private IIndividualEvaluationService individualEvaluationService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<IndividualEvaluation>> get(@RequestParam(value = "reports", required = false) List<String> reports) {
		LOGGER.info("Get");

		try {
			List<IndividualEvaluation> individualEvaluations = individualEvaluationService.read(getParameters(reports));

			return new ResponseEntity<List<IndividualEvaluation>>(individualEvaluations, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<IndividualEvaluation>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> reports) {
		Map<String, String[]> parameters = new HashMap<>();

		if (reports != null) {
			for (String report : reports) {
				parameters.put(EnumParameter.REPORT.getName(), new String[] { report });
			}
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody IndividualEvaluation individualEvaluation) {
		LOGGER.info("Post");

		try {
			individualEvaluationService.create(individualEvaluation);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<IndividualEvaluationUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			individualEvaluationService.update((List<IndividualEvaluationUpdateWrapper>) updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<IndividualEvaluation> individualEvaluations) {
		LOGGER.info("Delete");

		try {
			individualEvaluationService.delete(individualEvaluations);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
