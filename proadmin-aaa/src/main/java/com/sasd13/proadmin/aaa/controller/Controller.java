/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasd13.javaex.io.Stream;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.itf.RequestBean;

/**
 *
 * @author Samir
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = -5449641076971430518L;

	protected <T> RequestBean<T> readFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		String data = Stream.read(req.getReader());
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(data, new TypeReference<RequestBean<T>>() {
		});
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
