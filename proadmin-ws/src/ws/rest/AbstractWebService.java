/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.net.ws.DataSerializerException;

import ws.persistence.PersistenceService;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {
	
	private static final String REQUEST_PARAMETER_ID = "id";
	
	protected PersistenceService<T> persistenceService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		persistenceService = new PersistenceService<>(getEntityClass());
	}
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		
		Object respData = null;
		
		if (parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				respData = persistenceService.read(Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			List<T> list = (parameters.isEmpty())
					? persistenceService.readAll()
					: (!parameters.containsKey(REQUEST_PARAMETER_ID)) 
						? persistenceService.read(parameters)
						: new ArrayList<T>();
						
			respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
		}
		
		try {
			ParserService.parseAndWriteDataToResponse(req, resp, respData);
		} catch (DataSerializerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) ParserService.readAndParseDataFromRequest(req, getEntityClass());
			
			long id = persistenceService.create(t);
			
			ParserService.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataSerializerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			Object reqData = ParserService.readAndParseDataFromRequest(req, getEntityClass());
			
			if (reqData.getClass().isArray()) {
				persistenceService.updateAll((T[]) reqData);
			} else {
				persistenceService.update((T) reqData);
			}
		} catch (DataSerializerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		
		if (parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				persistenceService.delete(Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
