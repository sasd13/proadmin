package ws.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.javaex.ws.rest.MimeTypeParser;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RequestProcessor {
	
	public static void doGet(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		PersistenceService.setBeanClass(mClass);
		
		Object respData = (mapParameters.isEmpty())
				? PersistenceService.readAll()
        		: PersistenceService.read(mapParameters);
				
		MimeType mimeType = MimeTypeParser.decode(req.getContentType());
		String sRespData = DataParser.encode(respData, mimeType);
        
        ContentIO.write(resp.getWriter(), sRespData);
    }
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
    	String reqData = ContentIO.read(req.getReader());
    	
    	PersistenceService.setBeanClass(mClass);
    	
    	
    	Object object = DataParser.decode(reqData, mimeType, mClass);
    	
    	String respData = PersistenceService.create(reqData, mimeType);
        
        ContentIO.write(resp.getWriter(), respData);
	}
	
	public static void doPut(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
    	String reqData = ContentIO.read(req.getReader());
    	
    	PersistenceService.setBeanClass(mClass);
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	PersistenceService.update(reqData, mimeType);
    }
	
	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Class mClass) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
		
		PersistenceService.setBeanClass(mClass);
        
		if (!mapParameters.isEmpty()) {
			PersistenceService.delete(mapParameters);
		}
    }
}
