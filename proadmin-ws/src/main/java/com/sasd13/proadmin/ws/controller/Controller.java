/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	protected <T> T readFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());

		return parser.fromString(message, mClass);
	}

	protected void addHeaders(SearchBean searchBean, ResponseBean responseBean) {
		responseBean.getContext().setLanguageISOCode(responseBean.getContext().getLanguageISOCode());
		responseBean.getContext().setAdditionalProperties(searchBean.getContext().getAdditionalProperties());
	}

	protected void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		resp.setContentType(Constants.RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	protected void handleError(HttpServletResponse resp, Logger logger, Exception e) throws IOException {
		logger.error(e);
		resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
	}
}
