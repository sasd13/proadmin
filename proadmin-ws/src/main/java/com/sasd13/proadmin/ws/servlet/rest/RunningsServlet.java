/*
 * To change this license header, choose License Headers in Running Properties.
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
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningService;
import com.sasd13.proadmin.ws.service.ServiceFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/runnings")
public class RunningsServlet extends BeansServlet {

	private static final long serialVersionUID = -1274290275450535810L;

	private static final Logger LOGGER = Logger.getLogger(RunningsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : GET");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);
			List<Running> results = null;

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

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<Running> runnings = (List<Running>) readFromRequest(req, Running.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (Running running : runnings) {
				runningService.create(running);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : PUT");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<RunningUpdateWrapper> updateWrappers = (List<RunningUpdateWrapper>) readFromRequest(req, RunningUpdateWrapper.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (RunningUpdateWrapper updateWrapper : updateWrappers) {
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

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<Running> runnings = (List<Running>) readFromRequest(req, Running.class, null);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			for (Running running : runnings) {
				runningService.delete(running);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
