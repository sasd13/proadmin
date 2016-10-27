/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.net.EnumAAAError;
import com.sasd13.proadmin.ws.Config;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;
import com.sasd13.proadmin.ws.service.WSException;

/**
 *
 * @author Samir
 */
public abstract class BeansWebService<T> extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final String RESPONSE_CONTENT_TYPE = Config.getInfo(Names.WS_RESPONSE_CONTENT_TYPE);

	IReadService<T> readService;
	IManageService<T> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			readService = ReadServiceFactory.make(getBeanClass());
			manageService = ManageServiceFactory.make(getBeanClass());
		} catch (WSException e) {
			getLogger().error(e);
		}
	}

	protected abstract Class<T> getBeanClass();

	protected abstract String getWebServiceName();

	protected abstract Logger getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		getLogger().info(getWebServiceName() + " --> doGet");

		List<T> tsToResponse = new ArrayList<>();
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryEncoder.decode(parameters);

			if (!parameters.isEmpty()) {
				tsToResponse.addAll(readService.read(parameters));
			} else {
				tsToResponse.addAll(readService.readAll());
			}

			resp.setContentType(RESPONSE_CONTENT_TYPE);
			Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(tsToResponse));
		} catch (ParserException e) {
			doCatch(e, getWebServiceName() + " --> doGet failed", EnumAAAError.ERROR_PARSING_DATA, resp);
		} catch (ServiceException e) {
			doCatch(e, getWebServiceName() + " --> doGet failed", EnumAAAError.ERROR_SERVICE, resp);
		}
	}

	private void doCatch(Exception e, String logMessage, EnumAAAError aaaError, HttpServletResponse resp) throws IOException {
		getLogger().error(logMessage, e);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(aaaError.getCode()));
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.writeAndClose(resp.getWriter(), e.getMessage());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		getLogger().info(getWebServiceName() + " --> doPost");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			manageService.create(t);
		} catch (ParserException e) {
			doCatch(e, getWebServiceName() + " --> doPost failed", EnumAAAError.ERROR_PARSING_DATA, resp);
		} catch (ServiceException e) {
			doCatch(e, getWebServiceName() + " --> doPost failed", EnumAAAError.ERROR_SERVICE, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);

		getLogger().info(getWebServiceName() + " --> doPut");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			manageService.update(t);
		} catch (ParserException e) {
			doCatch(e, getWebServiceName() + " --> doPut failed", EnumAAAError.ERROR_PARSING_DATA, resp);
		} catch (ServiceException e) {
			doCatch(e, getWebServiceName() + " --> doPut failed", EnumAAAError.ERROR_SERVICE, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);

		getLogger().info(getWebServiceName() + " --> doDelete");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			manageService.delete(t);
		} catch (ParserException e) {
			doCatch(e, getWebServiceName() + " --> doDelete failed", EnumAAAError.ERROR_PARSING_DATA, resp);
		} catch (ServiceException e) {
			doCatch(e, getWebServiceName() + " --> doDelete failed", EnumAAAError.ERROR_SERVICE, resp);
		}
	}
}
