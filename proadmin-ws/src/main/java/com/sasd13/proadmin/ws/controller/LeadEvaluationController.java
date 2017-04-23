/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ILeadEvaluationService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

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
		LOGGER.info("[Proadmin-WS] LeadEvaluation : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);
			List<ILeadEvaluation> results = null;

			if (parameters.isEmpty()) {
				results = leadEvaluationService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = leadEvaluationService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ILeadEvaluation> iLeadEvaluations = (List<ILeadEvaluation>) readFromRequest(req, ILeadEvaluation.class, null);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			for (ILeadEvaluation iLeadEvaluation : iLeadEvaluations) {
				leadEvaluationService.create(iLeadEvaluation);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<LeadEvaluationUpdateWrapper> updateWrappers = (List<LeadEvaluationUpdateWrapper>) readFromRequest(req, LeadEvaluationUpdateWrapper.class, null);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			for (LeadEvaluationUpdateWrapper updateWrapper : updateWrappers) {
				leadEvaluationService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] LeadEvaluation : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ILeadEvaluation> iLeadEvaluations = (List<ILeadEvaluation>) readFromRequest(req, ILeadEvaluation.class, null);
			ILeadEvaluationService leadEvaluationService = (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class, dao);

			for (ILeadEvaluation iLeadEvaluation : iLeadEvaluations) {
				leadEvaluationService.delete(iLeadEvaluation);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
