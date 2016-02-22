package ws.rest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.ws.DataSerializer;
import com.sasd13.javaex.net.ws.DataSerializerException;

public class ParserService {
	
	public static <T> T readAndParseDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException, DataSerializerException {
		String sReqData = ContentIO.read(req.getReader());
		
		return (T) DataSerializer.fromString(req.getContentType(), sReqData, mClass);
	}
	
	public static void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException, DataSerializerException {
		String contentType = getRequestAcceptMimeType(req);
		
		resp.setContentType(contentType);
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_JSON);
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_XML);
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.TEXT_PLAIN);
		
		String sRespData = DataSerializer.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	private static String getRequestAcceptMimeType(HttpServletRequest req) {
		Enumeration<String> accepts = req.getHeaders(HttpRequest.HEADER_ATTRIBUTE_ACCEPT);
		
		String contentType = MimeType.TEXT_PLAIN, accept;
		
		while (accepts.hasMoreElements()) {
			accept = accepts.nextElement();
			
			if (MimeType.APPLICATION_JSON.equals(accept) || MimeType.APPLICATION_XML.equals(accept)) {
				contentType = accept;
				
				break;
			}
		}
		
		return contentType;
	}
}
