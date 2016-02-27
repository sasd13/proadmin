package ws.rest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.util.DataParser;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.javaex.util.MediaType;

public class IOService {
	
	public static <T> T readAndParseDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException, DataParserException {
		String sReqData = ContentIO.read(req.getReader());
		
		return (T) DataParser.fromString(req.getContentType(), sReqData, mClass);
	}
	
	public static void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException, DataParserException {
		String contentType = getRequestAcceptMediaType(req);
		
		resp.setContentType(contentType);
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MediaType.APPLICATION_JSON.getMIMEType());
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MediaType.APPLICATION_XML.getMIMEType());
		resp.addHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MediaType.TEXT_PLAIN.getMIMEType());
		
		String sRespData = DataParser.toString(contentType, respData);
		
		ContentIO.write(resp.getWriter(), sRespData);
	}
	
	private static String getRequestAcceptMediaType(HttpServletRequest req) {
		Enumeration<String> accepts = req.getHeaders(HttpRequest.HEADER_ATTRIBUTE_ACCEPT);
		
		String contentType = MediaType.TEXT_PLAIN.getMIMEType(), accept;
		
		while (accepts.hasMoreElements()) {
			accept = accepts.nextElement();
			
			if (MediaType.APPLICATION_JSON.getMIMEType().equals(accept) || MediaType.APPLICATION_XML.getMIMEType().equals(accept)) {
				contentType = accept;
				
				break;
			}
		}
		
		return contentType;
	}
}
