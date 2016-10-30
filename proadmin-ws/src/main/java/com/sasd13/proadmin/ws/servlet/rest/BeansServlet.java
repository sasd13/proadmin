/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.util.ws.EnumError;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;
import com.sasd13.proadmin.ws.service.WSException;

/**
 *
 * @author Samir
 */
public abstract class BeansServlet<T> extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.WS_RESPONSE_CONTENT_TYPE);

	IValidator<T> validator;
	IReadService<T> readService;
	IManageService<T> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			validator = ValidatorFactory.make(getBeanClass());
			readService = ReadServiceFactory.make(getBeanClass());
			manageService = ManageServiceFactory.make(getBeanClass());
		} catch (WSException | ValidatorException e) {
			getLogger().error(e);
		}
	}

	protected abstract Class<T> getBeanClass();

	protected abstract String getWebServiceName();

	protected abstract Logger getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doGet");

		List<T> list = new ArrayList<>();
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			if (!parameters.isEmpty()) {
				list = readService.read(parameters);
			} else {
				list = readService.readAll();
			}

			String results = ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(list);

			resp.setContentType(RESPONSE_CONTENT_TYPE);
			Stream.write(resp.getWriter(), results);

			getLogger().info("Message send from WS:" + results);
		} catch (ParserException e) {
			doCatch(e, "doGet failed", EnumError.DATA_PARSING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doGet failed", EnumError.SERVICE, resp);
		}
	}

	private void doCatch(Exception e, String logMessage, EnumError aaaError, HttpServletResponse resp) throws IOException {
		getLogger().error(logMessage, e);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(aaaError.getCode()));
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), e.getMessage());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info(getWebServiceName() + " --> doPost");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			validator.validate(t);
			manageService.create(t);
		} catch (ParserException e) {
			doCatch(e, "doPost failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doPost failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doPost failed", EnumError.SERVICE, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info(getWebServiceName() + " --> doPut");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			validator.validate(t);
			manageService.update(t);
		} catch (ParserException e) {
			doCatch(e, "doPut failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doPut failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doPut failed", EnumError.SERVICE, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info(getWebServiceName() + " --> doDelete");

		try {
			T t = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());

			validator.validate(t);
			manageService.delete(t);
		} catch (ParserException e) {
			doCatch(e, "doDelete failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doDelete failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doDelete failed", EnumError.SERVICE, resp);
		}
	}
}
