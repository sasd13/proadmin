/*
 * To change this license header, choose License Headers in IndividualEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;
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
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : read");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();
		IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);

		try {
			List<IndividualEvaluation> results = null;
			ResponseBean responseBean = new ResponseBean();

			if (parameters.isEmpty()) {
				results = individualEvaluationService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = individualEvaluationService.read(parameters);
			}

			responseBean.getContext().setPaginationCurrentItems(String.valueOf(results.size()));
			responseBean.setData(results);

			writeToResponse(resp, ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean));
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
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IndividualEvaluationBean> list = (List<IndividualEvaluationBean>) requestBean.getData();
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			// IndividualEvaluationAd

			for (IndividualEvaluationBean item : list) {
				// individualEvaluationService.create(individualEvaluation);
			}

			individualEvaluationService.create(individualEvaluations);
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
			List<IIndividualEvaluation> individualEvaluations = (List<IIndividualEvaluation>) readFromRequest(req, IIndividualEvaluation.class, null);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);

			for (IIndividualEvaluation individualEvaluation : individualEvaluations) {
				individualEvaluationService.delete(individualEvaluation);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
