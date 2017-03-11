/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.business.BusinessFactory;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.service.Service;
import com.sasd13.proadmin.service.ServiceFactory;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.util.wrapper.create.ReportCreateWrapper;
import com.sasd13.proadmin.ws.WSConstants;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsServlet extends BeansServlet<Report> {

	private static final long serialVersionUID = -574346007593042394L;

	@Override
	protected Class<Report> getBeanClass() {
		return Report.class;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			ReportCreateWrapper createWrapper = (ReportCreateWrapper) readFromRequest(req).get(0);

			validate(createWrapper);
			verify(createWrapper, dao);
			create(createWrapper, dao);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	private void validate(ReportCreateWrapper createWrapper) throws ValidatorException {
		ValidatorFactory.make(Report.class).validate(createWrapper.getReport());
		ValidatorFactory.make(LeadEvaluation.class).validate(createWrapper.getLeadEvaluation());

		for (IndividualEvaluation individualEvaluation : createWrapper.getIndividualEvaluations()) {
			ValidatorFactory.make(IndividualEvaluation.class).validate(individualEvaluation);
		}
	}

	private void verify(ReportCreateWrapper createWrapper, DAO dao) throws ValidatorException {
		BusinessFactory.make(Report.class).verify(dao, createWrapper.getReport());
		BusinessFactory.make(LeadEvaluation.class).verify(dao, createWrapper.getLeadEvaluation());

		for (IndividualEvaluation individualEvaluation : createWrapper.getIndividualEvaluations()) {
			BusinessFactory.make(IndividualEvaluation.class).verify(dao, individualEvaluation);
		}
	}

	private void create(ReportCreateWrapper createWrapper, DAO dao) {
		ServiceFactory.make(Report.class, dao).create(createWrapper.getReport());

		createWrapper.getLeadEvaluation().setReport(createWrapper.getReport());
		ServiceFactory.make(LeadEvaluation.class, dao).create(createWrapper.getLeadEvaluation());

		Service<IndividualEvaluation> service = ServiceFactory.make(IndividualEvaluation.class, dao);
		for (IndividualEvaluation individualEvaluation : createWrapper.getIndividualEvaluations()) {
			individualEvaluation.setReport(createWrapper.getReport());

			service.create(individualEvaluation);
		}
	}
}
