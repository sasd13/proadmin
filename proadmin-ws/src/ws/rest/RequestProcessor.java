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

@SuppressWarnings("rawtypes")
public class RequestProcessor<T> {
	
	private static final String HTTP_HEADER_ACCEPT = "Accept";
	
	private Class mClass;
	private PersistenceService<T> persistenceService;
	
	public RequestProcessor(Class mClass) {
		this.mClass = mClass;
		persistenceService = new PersistenceService<>(mClass);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameters = req.getParameterMap();
		
		if (parameters.size() == 1 && parameters.containsKey("id") && parameters.get("id").length == 1) {
			T t = null;
			
			try {
				t = persistenceService.read(Long.parseLong(req.getParameter("id")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			encodeAndWriteDataToResponse(req, resp, t);
		} else {
			T[] ts = null;
			
			if (!parameters.containsKey("id")) {
				List<T> list = persistenceService.readAll();
				
				if (!parameters.isEmpty()) {
					list = FilterService.filter(parameters, list, mClass);
				}
				
				ts = (T[]) list.toArray();
			}
			
			encodeAndWriteDataToResponse(req, resp, ts);
		}
	}
	
	private void encodeAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException {
		String contentType = ContentTypeSelector.select(req.getHeaders(HTTP_HEADER_ACCEPT));
		
		resp.setContentType(contentType);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_JSON);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_XML);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.TEXT_PLAIN);
		
		String sRespData = DataParser.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		T t = readAndDecodeDataFromRequest(req);		
		
		long id = persistenceService.create(t);
		
		encodeAndWriteDataToResponse(req, resp, id);
	}
	
	private T readAndDecodeDataFromRequest(HttpServletRequest req) throws IOException {
		T t = null;
		
		String reqData = ContentIO.read(req.getReader());
		
		try {
			t = (T) DataParser.fromString(req.getContentType(), reqData, mClass);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		T t = readAndDecodeDataFromRequest(req);
		
		persistenceService.update(t);
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
