package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.HttpFilter;

public class FilterFactory {
	
	public static HttpFilter make(Class mClass, Map<String, String[]> parameters) {
		HttpFilter httpFilter = null;
		
		if ("Teacher".equals(mClass.getSimpleName())) {
			httpFilter = new TeachersFilter(parameters);
		} else if ("Project".equals(mClass.getSimpleName())) {
			httpFilter = new ProjectsFilter(parameters);
		} else if ("Running".equals(mClass.getSimpleName())) {
			httpFilter = new RunningsFilter(parameters);
		} else if ("Team".equals(mClass.getSimpleName())) {
			httpFilter = new TeamsFilter(parameters);
		} else if ("Student".equals(mClass.getSimpleName())) {
			httpFilter = new StudentsFilter(parameters);
		} else if ("Report".equals(mClass.getSimpleName())) {
			httpFilter = new ReportsFilter(parameters);
		}
		
		return httpFilter;
	}
}
