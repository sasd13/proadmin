/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
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
import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.business.BusinessFactory;
import com.sasd13.proadmin.business.IBusiness;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.service.Service;
import com.sasd13.proadmin.service.ServiceFactory;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.exception.ErrorFactory;
import com.sasd13.proadmin.util.validator.UpdateWrapperValidatorFactory;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.util.wrapper.WrapperException;
import com.sasd13.proadmin.util.wrapper.update.UpdateWrapperFactory;
import com.sasd13.proadmin.ws.Names;
import com.sasd13.proadmin.ws.WSConstants;

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
	private IBusiness<T> business;

	protected abstract Class<T> getBeanClass();

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);

		try {
			validator = ValidatorFactory.make(getBeanClass());
			updateWrapperValidator = (IValidator<IUpdateWrapper<T>>) UpdateWrapperValidatorFactory.make(getBeanClass());
			business = (IBusiness<T>) BusinessFactory.make(getBeanClass());
		} catch (ValidatorException | ServiceException | BusinessException e) {
			LOGGER.error(e);
		}
	}

	private List<?> readFromRequest(HttpServletRequest req) throws IOException, ParserException {
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
		LOGGER.info("Message sent by WS : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	private void handleError(Exception e, HttpServletResponse resp) throws IOException {
		LOGGER.error(e);
		writeError(resp, HttpURLConnection.HTTP_INTERNAL_ERROR, ErrorFactory.make(e));
	}

	private void writeError(HttpServletResponse resp, int httpStatus, EnumError error) throws IOException {
		LOGGER.info("Error sent by WS : code=" + error.getCode());
		resp.setStatus(httpStatus);
		resp.addHeader(EnumHttpHeader.RESPONSE_ERROR.getName(), String.valueOf(error.getCode()));
		writeToResponse(resp, bundle.getString(error.getBundleKey()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doGet");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();
		List<T> results = new ArrayList<>();

		try {
			Service<T> service = ServiceFactory.make(getBeanClass(), dao);

			if (parameters.isEmpty()) {
				results = service.deepReadAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = service.deepRead(parameters);
			}

			writeToResponse(resp, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<T> ts = (List<T>) readFromRequest(req);
			Service<T> service = ServiceFactory.make(getBeanClass(), dao);

			for (T t : ts) {
				validator.validate(t);
				business.verify(dao, t);
				service.create(t);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPut");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<IUpdateWrapper<T>> updateWrappers = readUpdateWrappersFromRequest(req);
			Service<T> service = ServiceFactory.make(getBeanClass(), dao);

			for (IUpdateWrapper<T> updateWrapper : updateWrappers) {
				updateWrapperValidator.validate(updateWrapper);
				business.verify(dao, updateWrapper);
				service.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doDelete");

		DAO dao = (DAO) req.getAttribute(WSConstants.REQ_ATTR_DAO);

		try {
			List<T> ts = (List<T>) readFromRequest(req);
			Service<T> service = ServiceFactory.make(getBeanClass(), dao);

			for (T t : ts) {
				validator.validate(t);
				service.delete(t);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
