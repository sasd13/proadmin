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
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.exception.ErrorFactory;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;

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
		} catch (ValidatorException | ServiceException e) {
			getLogger().error(e);
		}
	}

	private T[] readFromRequest(HttpServletRequest req) throws ParserException, IOException {
		return ParserFactory.make(req.getContentType()).fromStringArray(Stream.readAndClose(req.getReader()), getBeanClass());
	}

	private void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		getLogger().info("Message send by WS : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	private void writeError(HttpServletResponse resp, EnumError error) throws IOException {
		getLogger().info("Error send by WS : code=" + error.getCode());
		resp.setHeader(EnumHttpHeader.RESPONSE_ERROR.getName(), String.valueOf(error.getCode()));
	}

	private void doCatch(Exception e, String logMessage, HttpServletResponse resp) throws IOException {
		getLogger().error(logMessage);

		EnumError error = ErrorFactory.make(e);
		String message = error != EnumError.UNKNOWN ? bundle.getString(error.getBundleKey()) + ". " + e.getMessage() : bundle.getString(error.getBundleKey());

		writeError(resp, error);
		writeToResponse(resp, message);
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
		} catch (Exception e) {
			doCatch(e, "doGet failed. " + e.getMessage(), resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doPost");

		try {
			T[] ts = readFromRequest(req);

			for (T t : ts) {
				validator.validate(t);
			}

			manageService.create(ts);
		} catch (Exception e) {
			doCatch(e, "doPost failed. " + e.getMessage(), resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doPut");

		try {
			T[] ts = readFromRequest(req);

			for (T t : ts) {
				validator.validate(t);
			}

			manageService.update(ts);
		} catch (Exception e) {
			doCatch(e, "doPut failed. " + e.getMessage(), resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLogger().info("doDelete");

		try {
			T[] ts = readFromRequest(req);

			for (T t : ts) {
				validator.validate(t);
			}

			manageService.delete(ts);
		} catch (Exception e) {
			doCatch(e, "doDelete failed. " + e.getMessage(), resp);
		}
	}
}
