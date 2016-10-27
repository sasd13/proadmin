/*
 * To change this license header, choose License Headers in IndividualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;

/**
 *
 * @author Samir
 */
@WebServlet("/individualevaluations")
public class IndividualEvaluationsServlet extends BeansServlet<IndividualEvaluation> {

	private static final long serialVersionUID = 1072007946993925027L;

	private static final Logger LOG = Logger.getLogger(IndividualEvaluationsServlet.class);

	@Override
	protected Class<IndividualEvaluation> getBeanClass() {
		return IndividualEvaluation.class;
	}

	@Override
	protected String getWebServiceName() {
		return "IndividualEvaluationsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
