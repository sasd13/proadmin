package ws.rest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;

public class ParserService {
	
	private static final String HTTP_HEADER_ACCEPT = "Accept";
	
	public static <T> T readAndParseDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		String sReqData = ContentIO.read(req.getReader());
		
		return (T) DataParser.fromString(req.getContentType(), sReqData, mClass);
	}
	
	public static void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException {
		String contentType = getRequestContentType(req);
		
		resp.setContentType(contentType);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_JSON);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.APPLICATION_XML);
		resp.addHeader(HTTP_HEADER_ACCEPT, MimeType.TEXT_PLAIN);
		
		String sRespData = DataParser.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	public static String getRequestContentType(HttpServletRequest req) {
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
}
