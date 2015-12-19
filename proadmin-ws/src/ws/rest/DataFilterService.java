package ws.rest;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class DataFilterService {

	private static Class mClass;
	
	public static void setEntityClass(Class mClass) {
		DataFilterService.mClass = mClass;
	}
	
	public static Object filter(Object object, Map<String, String[]> mapParameters) {		
		
		return null;
	}
}
