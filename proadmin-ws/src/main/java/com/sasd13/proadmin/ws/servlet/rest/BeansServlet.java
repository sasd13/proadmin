/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.exception.ErrorFactory;
import com.sasd13.proadmin.util.validator.UpdateWrapperValidatorFactory;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.util.wrapper.WrapperException;
import com.sasd13.proadmin.util.wrapper.update.UpdateWrapperFactory;
import com.sasd13.proadmin.ws.service.DeepReadServiceFactory;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;
import com.sasd13.proadmin.ws.util.Names;

/**
 *
 * @author Samir
 */
public abstract class BeansServlet<T> extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(BeansServlet.class);
	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.WS_RESPONSE_HEADER_CONTENT_TYPE);

	private TranslationBundle bundle;
	private IValidator<T> validator;
	private IValidator<IUpdateWrapper<T>> updateWrapperValidator;
	private IReadService<T> readService;
	private IDeepReadService<T> deepReadService;
	private IManageService<T> manageService;

	protected abstract Class<T> getBeanClass();

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);

		try {
			validator = ValidatorFactory.make(getBeanClass());
			updateWrapperValidator = (IValidator<IUpdateWrapper<T>>) UpdateWrapperValidatorFactory.make(getBeanClass());
			readService = ReadServiceFactory.make(getBeanClass());
			deepReadService = DeepReadServiceFactory.make(getBeanClass());
			manageService = ManageServiceFactory.make(getBeanClass());
		} catch (ValidatorException | ServiceException e) {
			LOGGER.error(e);
		}
	}

	private List<T> readFromRequest(HttpServletRequest req) throws IOException, ParserException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());

		return parser.fromStringArray(message, getBeanClass());
	}

	@SuppressWarnings("unchecked")
	private List<IUpdateWrapper<T>> readUpdateWrappersFromRequest(HttpServletRequest req) throws IOException, ParserException, WrapperException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());
		Class<?> mClass = UpdateWrapperFactory.make(getBeanClass()).getClass();

		return (List<IUpdateWrapper<T>>) parser.fromStringArray(message, mClass);
	}

	private void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		LOGGER.info("Message send by WS : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	private void handleError(Exception e, HttpServletResponse resp) throws IOException {
		LOGGER.error(e);
		writeError(resp, ErrorFactory.make(e));
	}

	private void writeError(HttpServletResponse resp, EnumError error) throws IOException {
		LOGGER.info("Error send by WS : code=" + error.getCode());
		resp.setHeader(EnumHttpHeader.RESPONSE_ERROR.getName(), String.valueOf(error.getCode()));

		String message = bundle.getString(error.getBundleKey());

		writeToResponse(resp, message);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doGet");

		List<T> results = new ArrayList<>();
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			if (parameters.isEmpty()) {
				results = Constants.WS_REQUEST_READ_DEEP.equalsIgnoreCase(req.getHeader(EnumHttpHeader.READ_CODE.getName())) ? deepReadService.deepReadAll() : readService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = Constants.WS_REQUEST_READ_DEEP.equalsIgnoreCase(req.getHeader(EnumHttpHeader.READ_CODE.getName())) ? deepReadService.deepRead(parameters) : readService.read(parameters);
			}

			String message = ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results);

			writeToResponse(resp, message);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		try {
			List<T> ts = readFromRequest(req);

			for (T t : ts) {
				validator.validate(t);
			}

			manageService.create(ts);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPut");

		try {
			List<IUpdateWrapper<T>> updateWrappers = readUpdateWrappersFromRequest(req);

			for (IUpdateWrapper<T> updateWrapper : updateWrappers) {
				updateWrapperValidator.validate(updateWrapper);
			}

			manageService.update(updateWrappers);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doDelete");

		try {
			List<T> ts = readFromRequest(req);

			for (T t : ts) {
				validator.validate(t);
			}

			manageService.delete(ts);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
