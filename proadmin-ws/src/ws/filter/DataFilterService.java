package ws.filter;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.core.bean.project.Project;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataFilterService {
	
	public static Object filter(List list, Map<String, String[]> mapParameters, Class mClass) {
		List result = null;
		
		if ("Project".equals(mClass.getSimpleName())) {
			result = new ProjectsFilter(mapParameters).filter((List<Project>) list);
		}
		
		return trim(result);
	}

	private static Object trim(List result) {
		if (result != null && result.size() == 1) {
			return result.get(0);
		} else {
			return result;
		}
	}
}
