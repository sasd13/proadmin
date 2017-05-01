package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.backend.service.IRunningTeamService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.RunningTeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.RunningTeamAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamRequestBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamResponseBean;

@RestController
@RequestMapping("/runningTeams")
public class RunningTeamController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamController.class);

	@Autowired
	private IRunningTeamService runningTeamService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody RunningTeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : create");

		try {
			runningTeamService.create(new RunningTeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody RunningTeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : update");

		try {
			runningTeamService.update(new RunningTeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody RunningTeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : delete");

		try {
			runningTeamService.delete(new RunningTeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] RunningTeam : search");

		try {
			List<RunningTeam> results = runningTeamService.read(searchBean.getCriterias());
			RunningTeamResponseBean responseBean = new RunningTeamResponseBean();
			List<RunningTeamBean> list = new ArrayList<>();
			RunningTeamAdapterM2I adapter = new RunningTeamAdapterM2I();

			for (RunningTeam result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
