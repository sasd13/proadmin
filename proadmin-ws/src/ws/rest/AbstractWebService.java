/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;

import ws.filter.FilterService;
import ws.persistence.PersistenceService;

/**
 *
 * @author Samir
 */
public abstract class AbstractWebService<T> extends HttpServlet {
	
	private static final String HTTP_HEADER_ACCEPT = "Accept";
	
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
					list = FilterService.filter(parameters, list, getEntityClass());
				}
				
				respData = list.toArray((T[]) Array.newInstance(getEntityClass(), list.size()));
			}
		}
		
		parseAndWriteDataToResponse(req, resp, respData);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		
		try {
			T t = (T) readAndParseDataFromRequest(req);
			
			id = persistenceService.create(t);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		
		parseAndWriteDataToResponse(req, resp, id);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object reqData = readAndParseDataFromRequest(req);
		
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
	
	private void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException {
		String contentType = getRequestContentType(req);
		
		resp.setContentType(contentType);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_JSON);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_XML);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.TEXT_PLAIN);
		
		String sRespData = DataParser.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	private String getRequestContentType(HttpServletRequest req) {
		Enumeration<String> accepts = req.getHeaders(HTTP_HEADER_ACCEPT);
		
		String contentType = null, accept;
		
		while (accepts.hasMoreElements()) {
			accept = accepts.nextElement();
			
			if (MimeType.APPLICATION_JSON.equals(accept) || MimeType.APPLICATION_XML.equals(accept)) {
				contentType = accept;
				
				break;
			}
		}
		
		return (contentType != null) ? contentType : MimeType.TEXT_PLAIN;
	}
	
	private Object readAndParseDataFromRequest(HttpServletRequest req) throws IOException {
		String sReqData = ContentIO.read(req.getReader());
		
		return DataParser.fromString(req.getContentType(), sReqData, getEntityClass());
	}
}
