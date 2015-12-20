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
import com.sasd13.javaex.parser.DataParser;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.javaex.ws.rest.MimeTypeParser;

@SuppressWarnings("rawtypes")
public class RequestProcessor {
	
	public static void doGet(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		DataPersistenceService.setEntityClass(mClass);
		
		Object respData;
		if (mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			respData = DataPersistenceService.read(Long.parseLong(req.getParameter("id")));
		} else {
			respData = DataPersistenceService.readAll();
			
			if (!mapParameters.isEmpty()) {
				DataFilterService.setEntityClass(mClass);
				respData = DataFilterService.filter((List) respData, mapParameters);
			}
		}
		
		MimeType mimeType = MimeTypeParser.decode(req.getContentType());
		String sRespData = DataParser.encode(respData, mimeType);
        
        ContentIO.write(resp.getWriter(), sRespData);
    }
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
    	String reqData = ContentIO.read(req.getReader());
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	Object object = DataParser.decode(reqData, mimeType, mClass);
    	
    	DataPersistenceService.setEntityClass(mClass);
    	long id = DataPersistenceService.create(object);
    	
    	String respData = DataParser.encode(id, mimeType);
        
        ContentIO.write(resp.getWriter(), respData);
	}
	
	public static void doPut(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		String reqData = ContentIO.read(req.getReader());
		
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	Object object = DataParser.decode(reqData, mimeType, mClass);
    	
    	DataPersistenceService.setEntityClass(mClass);
    	DataPersistenceService.update(object);
    }
	
	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		if (mapParameters.containsKey("id") && mapParameters.get("id").length == 1) {
			DataPersistenceService.setEntityClass(mClass);
			DataPersistenceService.delete(Long.parseLong(req.getParameter("id")));
		}
    }
}
