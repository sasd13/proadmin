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

import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.service.IRunningTeamService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.RunningTeamAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.RunningTeamAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

@RestController
@RequestMapping("/runningTeams")
public class RunningTeamController {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamController.class);

	@Autowired
	private IRunningTeamService runningTeamService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody RunningTeamBean runningTeamBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : create");

		try {
			runningTeamService.create(new RunningTeamAdapterI2B().adapt(runningTeamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody RunningTeamBean runningTeamBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : update");

		try {
			runningTeamService.update(new RunningTeamAdapterI2B().adapt(runningTeamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody RunningTeamBean runningTeamBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : delete");

		try {
			runningTeamService.delete(new RunningTeamAdapterI2B().adapt(runningTeamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<RunningTeamBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : search");

		try {
			List<RunningTeam> results = runningTeamService.read(request.getCriteria());
			ResponseBean<RunningTeamBean> response = new ResponseBean<RunningTeamBean>();
			RunningTeamAdapterB2I adapter = new RunningTeamAdapterB2I();

			for (RunningTeam result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<RunningTeamBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<RunningTeamBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
