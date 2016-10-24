/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.dao.LayeredPersistor;
import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.JDBCDAO;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.Config;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;
import com.sasd13.proadmin.ws.rest.handler.ReadHandler;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {

	private static final long serialVersionUID = 7016596177767882149L;

	private static final String REQUEST_PARAMETER_ID = "id";

	private DAO dao;

	static {
		try {
			JDBCDAO.init(
					Config.getInfo(Names.WS_DB_DRIVER), 
					Config.getInfo(Names.WS_DB_URL), 
					Config.getInfo(Names.WS_DB_USERNAME), 
					Config.getInfo(Names.WS_DB_PASSWORD)
			);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();

		dao = new JDBCDAO();
	}

	protected abstract Class<T> getEntityClass();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String headerRequestParameterized = (String) req.getHeader(EnumHttpHeaderField.REQUEST_PARAMETERIZED.getName());
		String headerDataRetrieve = (String) req.getHeader(EnumHttpHeaderField.DATA_RETRIEVE.getName());
		Map<String, String[]> parameters = req.getParameterMap();

		Object respData = null;
		LayeredPersistor persistor = new LayeredPersistor(dao);

		if (!EnumHttpHeaderValue.REQUEST_PARAMETERIZED_YES.getName().equalsIgnoreCase(headerRequestParameterized) && parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				long id = Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID));

				respData = ReadHandler.read(id, getEntityClass(), persistor, headerDataRetrieve);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			List<T> list;

			if (parameters.isEmpty()) {
				list = ReadHandler.readAll(getEntityClass(), persistor, headerDataRetrieve);
			} else {
				URLQueryEncoder.decode(parameters);
				list = ReadHandler.read(parameters, getEntityClass(), persistor, headerDataRetrieve);
			}

			respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
		}

		try {
			RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, respData);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) RESTHandler.readDataFromRequest(req, getEntityClass());

			LayeredPersistor persistor = new LayeredPersistor(dao);
			long id = persistor.create(t);

			RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, id);
		} catch (ParserException e) {
			e.printStackTrace();

			try {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_POST, null);
			} catch (ParserException e1) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String headerDataCollection = (String) req.getHeader(EnumHttpHeaderField.DATA_COLLECTION.getName());

		try {
			LayeredPersistor persistor = new LayeredPersistor(dao);

			if (EnumHttpHeaderValue.DATA_COLLECTION_YES.getName().equalsIgnoreCase(headerDataCollection)) {
				T[] ts = (T[]) RESTHandler.readArrayDataFromRequest(req, getEntityClass());
				persistor.updateAll(Arrays.asList(ts));
			} else {
				T t = (T) RESTHandler.readDataFromRequest(req, getEntityClass());
				persistor.update(t);
			}

			RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, null);
		} catch (ParserException e) {
			e.printStackTrace();

			try {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_PUT, null);
			} catch (ParserException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) RESTHandler.readDataFromRequest(req, getEntityClass());
			LayeredPersistor persistor = new LayeredPersistor(dao);

			persistor.delete(t);

			RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, null);
		} catch (ParserException e) {
			e.printStackTrace();

			try {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_DELETE, null);
			} catch (ParserException e1) {
				e1.printStackTrace();
			}
		}
	}
}
