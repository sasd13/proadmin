/*
 * To change this license header, choose License Headers in IndividualEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IIndividualEvaluationService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/individualEvaluations")
public class IndividualEvaluationController extends Controller {

	private static final long serialVersionUID = 1072007946993925027L;

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IIndividualEvaluation> results = null;

			if (parameters.isEmpty()) {
				results = individualEvaluationService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = individualEvaluationService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IIndividualEvaluation> iIndividualEvaluations = (List<IIndividualEvaluation>) readFromRequest(req, IIndividualEvaluation.class, null);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);

			for (IIndividualEvaluation iIndividualEvaluation : iIndividualEvaluations) {
				individualEvaluationService.create(iIndividualEvaluation);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IndividualEvaluationUpdate> updateWrappers = (List<IndividualEvaluationUpdate>) readFromRequest(req, IndividualEvaluationUpdate.class, null);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);

			for (IndividualEvaluationUpdate updateWrapper : updateWrappers) {
				individualEvaluationService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IIndividualEvaluation> iIndividualEvaluations = (List<IIndividualEvaluation>) readFromRequest(req, IIndividualEvaluation.class, null);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);

			for (IIndividualEvaluation iIndividualEvaluation : iIndividualEvaluations) {
				individualEvaluationService.delete(iIndividualEvaluation);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
