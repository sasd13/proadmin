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

	public static void doGet(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
        Map<String, String[]> mapParameters = req.getParameterMap();
        
        String respData;
        if (!mapParameters.isEmpty()) {
            respData = persistanceService.read(mapParameters);
        } else {
            respData = persistanceService.readAll();
        }
        
        ContentIO.write(resp.getWriter(), respData);
    }
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
    	String reqData = ContentIO.read(req.getReader());
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	String respData = persistanceService.create(reqData, mimeType);
        
        ContentIO.write(resp.getWriter(), respData);
    }

	public static void doPut(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
    	String reqData = ContentIO.read(req.getReader());
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	persistanceService.update(reqData, mimeType);
    }

	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
		Map<String, String[]> mapParameters = req.getParameterMap();
        
        if (!mapParameters.isEmpty()) {
            persistanceService.delete(mapParameters);
        } else {
            persistanceService.readAll();
        }
    }
}
