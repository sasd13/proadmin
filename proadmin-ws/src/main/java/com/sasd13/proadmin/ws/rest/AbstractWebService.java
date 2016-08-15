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

import com.sasd13.javaex.db.LayeredPersistor;
import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.db.JDBCDAO;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;
import com.sasd13.proadmin.ws.rest.handler.ReadHandler;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {
	
	private static final String REQUEST_PARAMETER_ID = "id";
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String headerRequestParameterized = (String) req.getHeader(EnumHttpHeaderField.REQUEST_PARAMETERIZED.getName());
		String headerDataRetrieve = (String) req.getHeader(EnumHttpHeaderField.DATA_RETRIEVE.getName());
		Map<String, String[]> parameters = req.getParameterMap();
		
		Object respData = null;
		boolean isCollection = false;
		LayeredPersistor persistor = new LayeredPersistor(JDBCDAO.create());
		
		if (!EnumHttpHeaderValue.REQUEST_PARAMETERIZED_YES.getName().equalsIgnoreCase(headerRequestParameterized)
				&& parameters.size() == 1 
				&& parameters.containsKey(REQUEST_PARAMETER_ID) 
				&& parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				long id = Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID));
				
				respData = ReadHandler.read(id, getEntityClass(), persistor, headerDataRetrieve);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			isCollection = true;
			
			List<T> list;
			
			if (parameters.isEmpty()) {
				list = ReadHandler.readAll(getEntityClass(), persistor, headerDataRetrieve);
			} else {
				list = ReadHandler.read(parameters, getEntityClass(), persistor, headerDataRetrieve);
			}
			
			respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
		}
		
		try {
			RESTHandler.parseAndWriteDataToResponse(req, resp, respData, isCollection);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) RESTHandler.readAndParseDataFromRequest(req, getEntityClass(), false);
			
			LayeredPersistor persistor = new LayeredPersistor(JDBCDAO.create());
			long id = persistor.create(t);
			
			RESTHandler.parseAndWriteDataToResponse(req, resp, id, false);
		} catch (ParserException e) {
			e.printStackTrace();
			
			try {
				RESTHandler.parseAndWriteDataToResponse(req, resp, EnumWSCode.ERROR_POST.getCode(), false);
			} catch (ParserException e1) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String headerDataCollection = (String) req.getHeader(EnumHttpHeaderField.DATA_COLLECTION.getName());
		
		try {
			LayeredPersistor persistor = new LayeredPersistor(JDBCDAO.create());
			
			if (EnumHttpHeaderValue.DATA_COLLECTION_YES.getName().equalsIgnoreCase(headerDataCollection)) {
				T[] ts = (T[]) RESTHandler.readAndParseDataFromRequest(req, getEntityClass(), true);
				persistor.updateAll(Arrays.asList(ts));
			} else {
				T t = (T) RESTHandler.readAndParseDataFromRequest(req, getEntityClass(), false);
				persistor.update(t);
			}
			
			RESTHandler.parseAndWriteDataToResponse(req, resp, EnumWSCode.OK.getCode(), false);
		} catch (ParserException e) {
			e.printStackTrace();
			
			try {
				RESTHandler.parseAndWriteDataToResponse(req, resp, EnumWSCode.ERROR_PUT.getCode(), false);
			} catch (ParserException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) RESTHandler.readAndParseDataFromRequest(req, getEntityClass(), false);
			LayeredPersistor persistor = new LayeredPersistor(JDBCDAO.create());
			
			persistor.delete(t);
			
			RESTHandler.parseAndWriteDataToResponse(req, resp, EnumWSCode.OK.getCode(), false);
		} catch (ParserException e) {
			e.printStackTrace();
			
			try {
				RESTHandler.parseAndWriteDataToResponse(req, resp, EnumWSCode.ERROR_DELETE.getCode(), false);
			} catch (ParserException e1) {
				e1.printStackTrace();
			}
		}
	}
}
