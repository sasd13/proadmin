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

import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.service.ITeamService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.TeamAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.TeamAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

@RestController
@RequestMapping("/team")
public class TeamController {

	private static final Logger LOGGER = Logger.getLogger(TeamController.class);

	@Autowired
	private ITeamService teamService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody TeamBean teamBean) {
		LOGGER.info("[Proadmin-Backend] Team : create");

		try {
			teamService.create(new TeamAdapterI2B().adapt(teamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody TeamBean teamBean) {
		LOGGER.info("[Proadmin-Backend] Team : update");

		try {
			teamService.update(new TeamAdapterI2B().adapt(teamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody TeamBean teamBean) {
		LOGGER.info("[Proadmin-Backend] Team : delete");

		try {
			teamService.delete(new TeamAdapterI2B().adapt(teamBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<TeamBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] Team : search");

		try {
			List<Team> results = teamService.read(request.getCriteria());
			ResponseBean<TeamBean> response = new ResponseBean<TeamBean>();
			TeamAdapterB2I adapter = new TeamAdapterB2I();

			for (Team result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<TeamBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<TeamBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
