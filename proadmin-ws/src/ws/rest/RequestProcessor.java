package ws.rest;

import java.io.IOException;
import java.lang.reflect.Array;
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

public class RequestProcessor<T> {
	
	private static final String HTTP_HEADER_ACCEPT = "Accept";
	
	private Class<T> mClass;
	private PersistenceService<T> persistenceService;
	
	public RequestProcessor(Class<T> mClass) {
		this.mClass = mClass;
		persistenceService = new PersistenceService<>(mClass);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
					list = FilterService.filter(parameters, list, mClass);
				}
				
				respData = list.toArray((T[]) Array.newInstance(mClass, list.size()));
			}
		}
		
		parseAndWriteDataToResponse(req, resp, respData);
	}
	
	private void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException {
		String contentType = ContentTypeSelector.select(req.getHeaders(HTTP_HEADER_ACCEPT));
		
		resp.setContentType(contentType);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_JSON);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_XML);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.TEXT_PLAIN);
		
		String sRespData = DataParser.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		
		try {
			T t = (T) readAndParseDataFromRequest(req);	
			
			id = persistenceService.create(t);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		
		parseAndWriteDataToResponse(req, resp, id);
	}
	
	private Object readAndParseDataFromRequest(HttpServletRequest req) throws IOException {
		String sReqData = ContentIO.read(req.getReader());
		
		return DataParser.fromString(req.getContentType(), sReqData, mClass);
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
