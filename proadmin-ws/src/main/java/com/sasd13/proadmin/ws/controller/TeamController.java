/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ITeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

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
		LOGGER.info("[Proadmin-WS] Team : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);
			List<ITeam> results = null;

			if (parameters.isEmpty()) {
				results = teamService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = teamService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ITeam> iTeams = (List<ITeam>) readFromRequest(req, ITeam.class, null);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			for (ITeam iTeam : iTeams) {
				teamService.create(iTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<TeamUpdate> updateWrappers = (List<TeamUpdate>) readFromRequest(req, TeamUpdate.class, null);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			for (TeamUpdate updateWrapper : updateWrappers) {
				teamService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Team : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ITeam> iTeams = (List<ITeam>) readFromRequest(req, ITeam.class, null);
			ITeamService teamService = (ITeamService) ServiceFactory.make(ITeamService.class, dao);

			for (ITeam iTeam : iTeams) {
				teamService.delete(iTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
