/*
 * To change this license header, choose License Headers in IndividualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.ws.Config;
import com.sasd13.proadmin.ws.service.IManageService;
import com.sasd13.proadmin.ws.service.IReadService;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/individualevaluations")
public class IndividualEvaluationsWebService extends HttpServlet {

	private static final long serialVersionUID = -2980193810543911111L;

	private static final Logger LOG = Logger.getLogger(IndividualEvaluation.class);
	private static final String RESPONSE_CONTENT_TYPE = Config.getInfo(Names.WS_RESPONSE_CONTENT_TYPE);

	IReadService<IndividualEvaluation> readService;
	IManageService<IndividualEvaluation> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		readService = ReadServiceFactory.make(IndividualEvaluation.class);
		manageService = ManageServiceFactory.make(IndividualEvaluation.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			if (parameters.isEmpty()) {
				//Inflate parameters to entity
				IndividualEvaluation individualEvaluationFromRequest = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), IndividualEvaluation.class);
				IndividualEvaluation individualEvaluationToResponse = readService.read(individualEvaluationFromRequest);
			} else {
				
			}
			
			

			resp.setContentType(RESPONSE_CONTENT_TYPE);
			Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(individualEvaluationToResponse));
		} catch (ParserException e) {
			LOG.error(e);
			// TODO : Send errors
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			IndividualEvaluation individualEvaluation = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), IndividualEvaluation.class);

			manageService.create(individualEvaluation);
		} catch (ParserException e) {
			LOG.error(e);
			// TODO : Send errors
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			IndividualEvaluation individualEvaluation = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), IndividualEvaluation.class);

			manageService.update(individualEvaluation);
		} catch (ParserException e) {
			LOG.error(e);
			// TODO : Send errors
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			IndividualEvaluation individualEvaluation = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), IndividualEvaluation.class);

			manageService.delete(individualEvaluation);
		} catch (ParserException e) {
			LOG.error(e);
			// TODO : Send errors
		}
	}
}
