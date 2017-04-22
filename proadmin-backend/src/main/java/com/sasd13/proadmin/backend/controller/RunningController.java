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
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.service.IRunningService;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

@RestController
@RequestMapping("/running")
public class RunningController {

	private static final Logger LOGGER = Logger.getLogger(RunningController.class);

	@Autowired
	private IRunningService runningService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Running>> get() {
		LOGGER.info("Get");

		try {
			List<Running> runnings = runningService.read(getParameters(null));

			return new ResponseEntity<List<Running>>(runnings, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Running>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> numbers) {
		Map<String, String[]> parameters = new HashMap<>();

		if (numbers != null) {
			// TODO
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody Running running) {
		LOGGER.info("Post");

		try {
			runningService.create(running);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<RunningUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			runningService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<Running> runnings) {
		LOGGER.info("Delete");

		try {
			runningService.delete(runnings);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
