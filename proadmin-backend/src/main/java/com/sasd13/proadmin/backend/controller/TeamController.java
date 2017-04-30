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

import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.backend.service.ITeamService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.TeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.TeamAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.itf.bean.team.TeamRequestBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;

@RestController
@RequestMapping("/teams")
public class TeamController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(TeamController.class);

	@Autowired
	private ITeamService teamService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody TeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Team : create");

		try {
			teamService.create(new TeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody TeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Team : update");

		try {
			teamService.update(new TeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody TeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Team : delete");

		try {
			teamService.delete(new TeamAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Team : search");

		try {
			List<Team> results = teamService.read(searchBean.getCriterias());
			TeamResponseBean responseBean = new TeamResponseBean();
			List<TeamBean> list = new ArrayList<>();
			TeamAdapterM2I adapter = new TeamAdapterM2I();

			for (Team result : results) {
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
