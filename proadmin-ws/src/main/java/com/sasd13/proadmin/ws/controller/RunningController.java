/*
 * To change this license header, choose License Headers in Running Properties.
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
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.itf.bean.running.RunningRequestBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IRunningService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.RunningAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.RunningAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.RunningUpdateAdapterI2B;

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
		LOGGER.info("[Proadmin-WS] Running : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);
			List<Running> results = runningService.read(parameters);
			RunningResponseBean responseBean = new RunningResponseBean();
			List<RunningBean> list = new ArrayList<>();
			RunningAdapterB2I adapter = new RunningAdapterB2I();

			for (Running result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size());

			writeToResponse(resp, responseBean);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningRequestBean requestBean = readFromRequest(req, RunningRequestBean.class);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			runningService.create(new RunningAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningRequestBean requestBean = readFromRequest(req, RunningRequestBean.class);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			runningService.update(new RunningUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Running : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RunningRequestBean requestBean = readFromRequest(req, RunningRequestBean.class);
			IRunningService runningService = (IRunningService) ServiceFactory.make(IRunningService.class, dao);

			runningService.delete(new RunningAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
