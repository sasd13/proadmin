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
		if (mapParameters.size() == 1 && mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			try {
				respData = PersistenceService.read(Long.parseLong(req.getParameter("id")), mClass);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			respData = PersistenceService.readAll(mClass);
			
			if (!mapParameters.isEmpty()) {
				respData = FilterService.filter((List) respData, mapParameters, mClass);
			}
		}
		
		parseAndWriteDataToResponse(resp, respData);
	}
	
	private static void parseAndWriteDataToResponse(HttpServletResponse resp, Object respData) throws IOException {
		String sRespData = DataParser.encode(MimeType.APPLICATION_JSON, respData);
		
		resp.setContentType(MimeType.APPLICATION_JSON);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Object object = readAndParseDataFromRequest(req, mClass);
		
		long respData = PersistenceService.create(object);
		
		parseAndWriteDataToResponse(resp, respData);
	}
	
	private static Object readAndParseDataFromRequest(HttpServletRequest req, Class mClass) throws IOException {
		String reqData = ContentIO.read(req.getReader());
		
		return DataParser.decode(req.getContentType(), reqData, mClass);
	}
	
	public static void doPut(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Object object = readAndParseDataFromRequest(req, mClass);
		
		PersistenceService.update(object);
	}
	
	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		if (mapParameters.size() == 1 && mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			try {
				PersistenceService.delete(Long.parseLong(req.getParameter("id")), mClass);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
