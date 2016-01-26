/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.filter.FilterFactory;
import ws.persistence.PersistenceService;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {
	
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
		
		if (parameters.size() == 1 && parameters.containsKey("id") && parameters.get("id").length == 1) {
			try {
				respData = persistenceService.read(Long.parseLong(req.getParameter("id")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			if (!parameters.containsKey("id")) {
				List<T> list = persistenceService.readAll();
				
				if (!parameters.isEmpty()) {
					list = FilterFactory.make(getEntityClass(), parameters).filter(list);
				}
				
				respData = (list.isEmpty()) 
						? null
						: list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
			}
		}
		
		ParserService.parseAndWriteDataToResponse(req, resp, respData);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		
		try {
			T t = (T) ParserService.readAndParseDataFromRequest(req, getEntityClass());
			
			id = persistenceService.create(t);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		
		ParserService.parseAndWriteDataToResponse(req, resp, id);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object reqData = ParserService.readAndParseDataFromRequest(req, getEntityClass());
		
		try {
			if (reqData.getClass().isArray()) {
				persistenceService.updateAll((T[]) reqData);
			} else {
				persistenceService.update((T) reqData);
			}
		} catch (NullPointerException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		
		if (parameters.size() == 1 && parameters.containsKey("id") && parameters.get("id").length == 1) {
			try {
				persistenceService.delete(Long.parseLong(req.getParameter("id")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
