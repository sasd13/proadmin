package ws.rest;

import java.util.Enumeration;

import com.sasd13.javaex.net.MimeType;

public class ContentTypeSelector {
	
	public static String select(Enumeration<String> accepts) {
		String contentType = null;
		
		String accept;
		
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
