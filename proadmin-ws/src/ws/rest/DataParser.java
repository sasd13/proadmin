package ws.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DataParser {

	private static Gson gson = new GsonBuilder().create();
	
	public static String encode(Object object, MimeType mimeType) {
		String sData = null;
		
		if (mimeType == MimeType.JSON) {
			sData = gson.toJson(object);
		} else if (mimeType == MimeType.XML) {
			//TODO
		} else {
			sData = object.toString();
		}
		
		return sData;
	}
	
	public static Object decode(String sData, MimeType mimeType, Class mClass) {
		Object object = null;
		
		if (mimeType == MimeType.JSON) {
			if (sData.startsWith("{") && sData.endsWith("}")) {
				object = gson.fromJson(sData, mClass);
			} else if (sData.startsWith("[") && sData.endsWith("]")) {
				try {
					Class mTabClass = Class.forName("[L " + mClass.getName());
					object = gson.fromJson(sData, mTabClass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else if (mimeType == MimeType.XML) {
			//TODO
		} else {
			
		}
		
		return object;
	}
}
