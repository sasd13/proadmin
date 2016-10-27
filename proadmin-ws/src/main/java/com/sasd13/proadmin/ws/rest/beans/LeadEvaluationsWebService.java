/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest.beans;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.LeadEvaluation;

/**
 *
 * @author Samir
 */
@WebServlet("/leadevaluations")
public class LeadEvaluationsWebService extends BeansWebService<LeadEvaluation> {

	private static final long serialVersionUID = 9187941265220564458L;

	private static final Logger LOG = Logger.getLogger(LeadEvaluationsWebService.class);

	@Override
	protected Class<LeadEvaluation> getBeanClass() {
		return LeadEvaluation.class;
	}

	@Override
	protected String getWebServiceName() {
		return "LeadEvaluationsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
