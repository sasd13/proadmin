/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.proadmin.bean.running.LeadEvaluation;

/**
 *
 * @author Samir
 */
@WebServlet("/leadevaluations")
public class LeadEvaluationsWebService extends AbstractWebService<LeadEvaluation> {

	@Override
	protected Class<LeadEvaluation> getEntityClass() {
		return LeadEvaluation.class;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Do nothing
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Do nothing
	}
}
