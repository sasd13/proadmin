package ws.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.filter.FilterService;
import ws.persistence.PersistenceService;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;

@SuppressWarnings("rawtypes")
public class RequestProcessor {
	
	public static void doGet(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		Object respData = null;
		if (mapParameters.containsKey("id")) {
			if (mapParameters.get("id").length == 1) {
				respData = PersistenceService.read(Long.parseLong(req.getParameter("id")), mClass);
			}
		} else {
			respData = PersistenceService.readAll(mClass);
			
			if (!mapParameters.isEmpty()) {
				respData = FilterService.filter((List) respData, mapParameters, mClass);
			}
		}
		
		String sRespData = DataParser.encode(MimeType.APPLICATION_JSON, respData);
		resp.setContentType(MimeType.APPLICATION_JSON);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		String reqData = ContentIO.read(req.getReader());
		
		Object object = DataParser.decode(reqData, req.getContentType(), mClass);
		
		long id = PersistenceService.create(object);
		
		String sRespData = DataParser.encode(MimeType.APPLICATION_JSON, id);
		resp.setContentType(MimeType.APPLICATION_JSON);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public static void doPut(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		String reqData = ContentIO.read(req.getReader());
		
		Object object = DataParser.decode(req.getContentType(), reqData, mClass);
		
		PersistenceService.update(object);
	}
	
	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		if (mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			PersistenceService.delete(Long.parseLong(req.getParameter("id")), mClass);
		}
	}
}
