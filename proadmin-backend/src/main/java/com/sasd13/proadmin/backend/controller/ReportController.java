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

import com.sasd13.proadmin.backend.service.IReportService;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

@RestController
@RequestMapping("/report")
public class ReportController {

	private static final Logger LOGGER = Logger.getLogger(ReportController.class);

	@Autowired
	private IReportService reportService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Report>> get(@RequestParam(value = "numbers", required = false) List<String> numbers) {
		LOGGER.info("Get");

		try {
			List<Report> reports = reportService.read(getParameters(numbers));

			return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Report>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> numbers) {
		Map<String, String[]> parameters = new HashMap<>();

		if (numbers != null) {
			for (String number : numbers) {
				parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });
			}
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody Report report) {
		LOGGER.info("Post");

		try {
			reportService.create(report);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<ReportUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			reportService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<Report> reports) {
		LOGGER.info("Delete");

		try {
			reportService.delete(reports);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
