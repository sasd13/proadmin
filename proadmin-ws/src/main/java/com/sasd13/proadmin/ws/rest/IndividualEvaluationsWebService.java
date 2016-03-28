/*
 * To change this license header, choose License Headers in IndividualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;

/**
 *
 * @author Samir
 */
@WebServlet("/individualevaluations")
public class IndividualEvaluationsWebService extends AbstractWebService<IndividualEvaluation> {
	
	@Override
	protected Class<IndividualEvaluation> getEntityClass() {
		return IndividualEvaluation.class;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Do nothing
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Do nothing
	}
}
