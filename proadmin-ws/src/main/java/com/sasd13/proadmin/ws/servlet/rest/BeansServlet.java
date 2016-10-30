/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.i18n.TranslationBundle;
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

	private TranslationBundle bundle;
	private IValidator<T> validator;
	private IReadService<T> readService;
	private IManageService<T> manageService;

	protected abstract Class<T> getBeanClass();

	protected abstract Logger getLogger();

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);

		try {
			validator = ValidatorFactory.make(getBeanClass());
			readService = ReadServiceFactory.make(getBeanClass());
			manageService = ManageServiceFactory.make(getBeanClass());
		} catch (ValidatorException | WSException e) {
			getLogger().error(e);
		}
	}

	private T readFromRequest(HttpServletRequest req) throws ParserException, IOException {
		return ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), getBeanClass());
	}

	private void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	private void doCatch(String logMessage, HttpServletResponse resp, EnumError error) throws IOException {
		getLogger().error(logMessage);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(error.getCode()));
		writeToResponse(resp, bundle.getString(error.getBundleKey()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doGet");

		Map<String, String[]> parameters = req.getParameterMap();

		URLQueryUtils.decode(parameters);

		try {
			List<T> results = !parameters.isEmpty() ? readService.read(parameters) : readService.readAll();
			String message = ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results);

			writeToResponse(resp, message);
			getLogger().info("Message send by WS: " + message);
		} catch (ParserException e) {
			doCatch("doGet failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ServiceException e) {
			doCatch("doGet failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doPost");

		try {
			T t = readFromRequest(req);

			validator.validate(t);
			manageService.create(t);
		} catch (ParserException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doPut");

		try {
			T t = readFromRequest(req);

			validator.validate(t);
			manageService.update(t);
		} catch (ParserException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doDelete");

		try {
			T t = readFromRequest(req);

			validator.validate(t);
			manageService.delete(t);
		} catch (ParserException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}
}
