package ws.filter;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.core.bean.project.Project;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DataFilterService {

	private static Class mClass;
	
	public static void setEntityClass(Class mClass) {
		DataFilterService.mClass = mClass;
	}
	
	public static Object filter(List list, Map<String, String[]> mapParameters) {
		List result = null;
		
		if ("Project".equals(mClass.getSimpleName())) {
			result = new ProjectsFilter(mapParameters).filter((List<Project>) list);
		}
		
		if (result != null && result.size() == 1) {
			return result.get(0);
		} else {
			return result;
		}
	}
}
