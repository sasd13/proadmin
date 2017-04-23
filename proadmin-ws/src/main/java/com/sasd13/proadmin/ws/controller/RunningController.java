/*
 * To change this license header, choose License Headers in Running Properties.
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
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/runnings")
public class RunningController extends Controller {

	private static final long serialVersionUID = -1274290275450535810L;

	private static final Logger LOGGER = Logger.getLogger(RunningController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);
			List<IRunning> results = null;

			if (parameters.isEmpty()) {
				results = runningService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = runningService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IRunning> iRunnings = (List<IRunning>) readFromRequest(req, IRunning.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (IRunning iRunning : iRunnings) {
				runningService.create(iRunning);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<RunningUpdate> updateWrappers = (List<RunningUpdate>) readFromRequest(req, RunningUpdate.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (RunningUpdate updateWrapper : updateWrappers) {
				runningService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IRunning> iRunnings = (List<IRunning>) readFromRequest(req, IRunning.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (IRunning iRunning : iRunnings) {
				runningService.delete(iRunning);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
