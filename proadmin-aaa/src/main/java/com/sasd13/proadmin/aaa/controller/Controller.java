/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.EnumHttpStatus;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.itf.ResponseBean;

/**
 *
 * @author Samir
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = -5449641076971430518L;

	protected <T> T readFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		String message = Stream.read(req.getReader());
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(message, mClass);
	}

	protected void addHeaders(ResponseBean responseBean, int size) {
		responseBean.getHeader().getApplicativeContext().setLanguageISOCode(responseBean.getHeader().getApplicativeContext().getLanguageISOCode());
		responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(size));
	}

	protected void writeToResponse(HttpServletResponse resp, ResponseBean responseBean) throws IOException {
		String message = ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean);

		resp.setContentType(Constants.RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	protected void handleError(HttpServletResponse resp, Logger logger, Exception e) throws IOException {
		logger.error(e);
		resp.sendError(EnumHttpStatus.EXPECTATION_FAILED.getCode());
	}
}
