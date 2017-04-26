/*
 * To change this license header, choose License Headers in Student Properties.
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
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamRequestBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamResponseBean;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningTeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.RunningTeamAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.RunningTeamAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.RunningTeamUpdateAdapterI2B;

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
		LOGGER.info("[Proadmin-WS] RunningTeam : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);
			List<RunningTeam> results = runningTeamService.read(parameters);
			RunningTeamResponseBean responseBean = new RunningTeamResponseBean();
			List<RunningTeamBean> list = new ArrayList<>();
			RunningTeamAdapterB2I adapter = new RunningTeamAdapterB2I();

			for (RunningTeam result : results) {
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
		LOGGER.info("[Proadmin-WS] RunningTeam : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningTeamRequestBean requestBean = readFromRequest(req, RunningTeamRequestBean.class);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			runningTeamService.create(new RunningTeamAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningTeamRequestBean requestBean = readFromRequest(req, RunningTeamRequestBean.class);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			runningTeamService.update(new RunningTeamUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] RunningTeam : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningTeamRequestBean requestBean = readFromRequest(req, RunningTeamRequestBean.class);
			IRunningTeamService runningTeamService = (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class, dao);

			runningTeamService.delete(new RunningTeamAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
