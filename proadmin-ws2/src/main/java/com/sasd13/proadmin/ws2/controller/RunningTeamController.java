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
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IRunningTeamService;

@RestController
@RequestMapping("/runningTeam")
public class RunningTeamController {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamController.class);

	@Autowired
	private IRunningTeamService runningTeamService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RunningTeam>> get() {
		LOGGER.info("Get");

		try {
			List<RunningTeam> runningTeams = runningTeamService.read(getParameters(null));

			return new ResponseEntity<List<RunningTeam>>(runningTeams, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<RunningTeam>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> numbers) {
		Map<String, String[]> parameters = new HashMap<>();

		if (numbers != null) {
			// TODO
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody RunningTeam runningTeam) {
		LOGGER.info("Post");

		try {
			runningTeamService.create(runningTeam);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<RunningTeamUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			runningTeamService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<RunningTeam> runningTeams) {
		LOGGER.info("Delete");

		try {
			runningTeamService.delete(runningTeams);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
