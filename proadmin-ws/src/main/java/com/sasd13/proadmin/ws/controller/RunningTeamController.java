/*
 * To change this license header, choose License Headers in Student Properties.
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
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningTeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/runningTeams")
public class RunningTeamController extends Controller {

	private static final long serialVersionUID = -8420663078069520851L;

	private static final Logger LOGGER = Logger.getLogger(RunningTeamController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);
			List<IRunningTeam> results = null;

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

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IRunningTeam> iRunningTeams = (List<IRunningTeam>) readFromRequest(req, IRunningTeam.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (IRunningTeam iRunningTeam : iRunningTeams) {
				runningTeamService.create(iRunningTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<RunningTeamUpdate> updateWrappers = (List<RunningTeamUpdate>) readFromRequest(req, RunningTeamUpdate.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (RunningTeamUpdate updateWrapper : updateWrappers) {
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

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IRunningTeam> iRunningTeams = (List<IRunningTeam>) readFromRequest(req, IRunningTeam.class, null);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			for (IRunningTeam iRunningTeam : iRunningTeams) {
				runningTeamService.delete(iRunningTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
