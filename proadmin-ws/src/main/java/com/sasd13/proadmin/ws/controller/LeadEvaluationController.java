/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
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
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationRequestBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationResponseBean;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ILeadEvaluationService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.LeadEvaluationAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.LeadEvaluationAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.LeadEvaluationUpdateAdapterI2B;

/**
 *
 * @author Samir
 */
@WebServlet("/leadEvaluations")
public class LeadEvaluationController extends Controller {

	private static final long serialVersionUID = 9187941265220564458L;

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);
			List<LeadEvaluation> results = leadEvaluationService.read(parameters);
			LeadEvaluationResponseBean responseBean = new LeadEvaluationResponseBean();
			List<LeadEvaluationBean> list = new ArrayList<>();
			LeadEvaluationAdapterB2I adapter = new LeadEvaluationAdapterB2I();

			for (LeadEvaluation result : results) {
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
		LOGGER.info("[Proadmin-WS] LeadEvaluation : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			LeadEvaluationRequestBean requestBean = readFromRequest(req, LeadEvaluationRequestBean.class);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			leadEvaluationService.create(new LeadEvaluationAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			LeadEvaluationRequestBean requestBean = readFromRequest(req, LeadEvaluationRequestBean.class);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			leadEvaluationService.update(new LeadEvaluationUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			LeadEvaluationRequestBean requestBean = readFromRequest(req, LeadEvaluationRequestBean.class);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			leadEvaluationService.delete(new LeadEvaluationAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
