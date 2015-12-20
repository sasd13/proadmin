package ws.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.filter.DataFilterService;
import ws.persistence.DataPersistenceService;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.parser.DataParser;

@SuppressWarnings("rawtypes")
public class RequestProcessor {
	
	public static void doGet(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		Object respData;
		if (mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			respData = DataPersistenceService.read(Long.parseLong(req.getParameter("id")), mClass);
		} else {
			respData = DataPersistenceService.readAll(mClass);
			
			if (!mapParameters.isEmpty()) {
				respData = DataFilterService.filter((List) respData, mapParameters, mClass);
			}
		}
		
		String sRespData = DataParser.encode(req.getContentType(), respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		String reqData = ContentIO.read(req.getReader());
		
		Object object = DataParser.decode(reqData, req.getContentType(), mClass);
		
		long id = DataPersistenceService.create(object);
		
		String respData = DataParser.encode(req.getContentType(), id);
		
		ContentIO.write(resp.getWriter(), respData);
	}
	
	public static void doPut(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		String reqData = ContentIO.read(req.getReader());
		
		Object object = DataParser.decode(req.getContentType(), reqData, mClass);
		
		DataPersistenceService.update(object);
	}
	
	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		if (mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			DataPersistenceService.delete(Long.parseLong(req.getParameter("id")), mClass);
		}
	}
}
