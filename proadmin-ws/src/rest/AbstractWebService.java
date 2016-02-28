/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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
import com.sasd13.javaex.util.DataParserException;

import db.JDBCDAO;

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
		
		Object respData = null;
		
		if (parameters.size() == 1 && parameters.containsKey(REQUEST_PARAMETER_ID) && parameters.get(REQUEST_PARAMETER_ID).length == 1) {
			try {
				respData = persistence.read(Long.parseLong(req.getParameter(REQUEST_PARAMETER_ID)), getEntityClass());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			List<T> list = (parameters.isEmpty())
					? persistence.readAll(getEntityClass())
					: (!parameters.containsKey(REQUEST_PARAMETER_ID)) 
						? persistence.read(parameters, getEntityClass())
						: new ArrayList<T>();
			
			respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
		}
		
		try {
			IOService.parseAndWriteDataToResponse(req, resp, respData);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			T t = (T) IOService.readAndParseDataFromRequest(req, getEntityClass());
			
			long id = persistence.create(t);
			
			IOService.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataParserException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			Object reqData = IOService.readAndParseDataFromRequest(req, getEntityClass());
			
			if (reqData.getClass().isArray()) {
				persistence.updateAll((T[]) reqData);
			} else {
				persistence.update((T) reqData);
			}
		} catch (DataParserException | ClassCastException e) {
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
