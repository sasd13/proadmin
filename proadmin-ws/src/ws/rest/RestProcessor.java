package ws.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.javaex.ws.rest.MimeTypeParser;

import ws.ContentIO;

@SuppressWarnings("rawtypes")
public class RestProcessor {

	public static void doGet(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        String respData;
        if (paramsId != null) {
            respData = persistanceService.read(paramsId);
        } else {
            respData = persistanceService.readAll();
        }
        
        ContentIO.write(resp, respData);
    }
	
	public static void doPost(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
    	String reqData = ContentIO.read(req);
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	String respData = persistanceService.create(reqData, mimeType);
        
        ContentIO.write(resp, respData);
    }

	public static void doPut(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
    	String reqData = ContentIO.read(req);
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	persistanceService.update(reqData, mimeType);
    }

	public static void doDelete(HttpServletRequest req, HttpServletResponse resp, PersistenceService persistanceService) throws ServletException, IOException {
    	String paramsId = req.getParameter("id");
        
        String respData;
        if (paramsId != null) {
            respData = persistanceService.read(paramsId);
        } else {
            respData = persistanceService.readAll();
        }
        
        ContentIO.write(resp, respData);
    }
}
