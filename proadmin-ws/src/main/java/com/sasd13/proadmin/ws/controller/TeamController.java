/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.itf.bean.team.TeamRequestBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ITeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.TeamAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.TeamAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.TeamUpdateAdapterI2B;

/**
 *
 * @author Samir
 */
@WebServlet("/teams")
public class TeamController extends Controller {

	private static final long serialVersionUID = -416118742023104197L;

	private static final Logger LOGGER = Logger.getLogger(TeamController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);
			List<Team> results = teamService.read(parameters);
			TeamResponseBean responseBean = new TeamResponseBean();
			List<TeamBean> list = new ArrayList<>();
			TeamAdapterB2I adapter = new TeamAdapterB2I();

			for (Team result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size());

			writeToResponse(resp, ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeamRequestBean requestBean = readFromRequest(req, TeamRequestBean.class);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			teamService.create(new TeamAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeamRequestBean requestBean = readFromRequest(req, TeamRequestBean.class);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			teamService.update(new TeamUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeamRequestBean requestBean = readFromRequest(req, TeamRequestBean.class);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			teamService.delete(new TeamAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
