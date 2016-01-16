package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;

public class FilterFactory {
	
	public static AbstractFilter make(Class mClass, Map<String, String[]> parameters) {
		AbstractFilter filter = null;
		
		if ("Teacher".equals(mClass.getSimpleName())) {
			filter = new TeachersFilter(parameters);
		} else if ("Project".equals(mClass.getSimpleName())) {
			filter = new ProjectsFilter(parameters);
		} else if ("Running".equals(mClass.getSimpleName())) {
			filter = new RunningsFilter(parameters);
		} else if ("Team".equals(mClass.getSimpleName())) {
			filter = new TeamsFilter(parameters);
		} else if ("Student".equals(mClass.getSimpleName())) {
			filter = new StudentsFilter(parameters);
		} else if ("Report".equals(mClass.getSimpleName())) {
			filter = new ReportsFilter(parameters);
		}
		
		return filter;
	}
}
