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
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationRequestBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationResponseBean;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IIndividualEvaluationService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.IndividualEvaluationAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.IndividualEvaluationAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.IndividualEvaluationUpdateAdapterI2B;

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
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IndividualEvaluation> results = individualEvaluationService.read(parameters);
			IndividualEvaluationResponseBean responseBean = new IndividualEvaluationResponseBean();
			List<IndividualEvaluationBean> list = new ArrayList<>();
			IndividualEvaluationAdapterB2I adapter = new IndividualEvaluationAdapterB2I();

			for (IndividualEvaluation result : results) {
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
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			IndividualEvaluationRequestBean requestBean = readFromRequest(req, IndividualEvaluationRequestBean.class);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			IndividualEvaluationAdapterI2B adapter = new IndividualEvaluationAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.create(individualEvaluations);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			IndividualEvaluationRequestBean requestBean = readFromRequest(req, IndividualEvaluationRequestBean.class);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IndividualEvaluationUpdate> individualEvaluations = new ArrayList<>();
			IndividualEvaluationUpdateAdapterI2B adapter = new IndividualEvaluationUpdateAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.update(individualEvaluations);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] IndividualEvaluation : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			IndividualEvaluationRequestBean requestBean = readFromRequest(req, IndividualEvaluationRequestBean.class);
			IIndividualEvaluationService individualEvaluationService = (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class, dao);
			List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
			IndividualEvaluationAdapterI2B adapter = new IndividualEvaluationAdapterI2B();

			for (IndividualEvaluationBean individualEvaluationBean : requestBean.getData()) {
				individualEvaluations.add(adapter.adapt(individualEvaluationBean));
			}

			individualEvaluationService.delete(individualEvaluations);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
