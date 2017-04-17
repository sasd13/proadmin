/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningTeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/runningteams")
public class RunningTeamsServlet extends BeansServlet {

	private static final long serialVersionUID = -8420663078069520851L;

	private static final Logger LOGGER = Logger.getLogger(RunningTeamsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : GET");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);
			List<RunningTeam> results = null;

			if (parameters.isEmpty()) {
				results = runningTeamService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = runningTeamService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : POST");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<RunningTeam> runningTeams = (List<RunningTeam>) readFromRequest(req, RunningTeam.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (RunningTeam runningTeam : runningTeams) {
				runningTeamService.create(runningTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : PUT");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<RunningTeamUpdateWrapper> updateWrappers = (List<RunningTeamUpdateWrapper>) readFromRequest(req, RunningTeamUpdateWrapper.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (RunningTeamUpdateWrapper updateWrapper : updateWrappers) {
				runningTeamService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : DELETE");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<RunningTeam> runningTeams = (List<RunningTeam>) readFromRequest(req, RunningTeam.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (RunningTeam runningTeam : runningTeams) {
				runningTeamService.delete(runningTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
