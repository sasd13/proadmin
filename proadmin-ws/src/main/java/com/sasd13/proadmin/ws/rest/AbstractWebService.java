/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.db.Persistence;
import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.ws.db.JDBCDAO;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;
import com.sasd13.proadmin.ws.rest.handler.ReadHandler;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {
	
	private static final String REQUEST_PARAMETER_ID = "id";
	
	private Persistence persistence;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		persistence = new Persistence(JDBCDAO.getInstance());
	}
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		String headerDataRetrieve = (String) req.getHeader(HttpHeader.DATA_RETRIEVE_FIELD.getName());
		
		Object respData = null;
		
		if (parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				long id = Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID));
				
				respData = ReadHandler.read(id, getEntityClass(), persistence, headerDataRetrieve);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			List<T> list;
			
			if (parameters.isEmpty()) {
				list = ReadHandler.readAll(getEntityClass(), persistence, headerDataRetrieve);
			} else if (!parameters.containsKey(REQUEST_PARAMETER_ID)) {
				list = ReadHandler.read(parameters, getEntityClass(), persistence, headerDataRetrieve);
			} else {
				list = new ArrayList<>();
			}
			
			respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
		}
		
		try {
			RESTHandler.parseAndWriteDataToResponse(req, resp, respData);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) RESTHandler.readAndParseDataFromRequest(req, getEntityClass());
			
			long id = persistence.create(t);
			
			RESTHandler.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Object reqData = RESTHandler.readAndParseDataFromRequest(req, getEntityClass());
			
			if (reqData.getClass().isArray()) {
				persistence.updateAll((T[]) reqData);
			} else {
				persistence.update((T) reqData);
			}
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		
		if (parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				persistence.delete(Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID)), getEntityClass());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
