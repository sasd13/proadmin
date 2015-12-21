package ws.filter;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FilterService {
	
	public static Object filter(List list, Map<String, String[]> mapParameters, Class mClass) {
		List result = null;
		
		if ("Project".equals(mClass.getSimpleName())) {
			result = new ProjectsFilter(mapParameters).filter((List<Project>) list);
		} else if ("Report".equals(mClass.getSimpleName())) {
			result = new ReportsFilter(mapParameters).filter((List<Report>) list);
		} else if ("Running".equals(mClass.getSimpleName())) {
			result = new RunningsFilter(mapParameters).filter((List<Running>) list);
		} else if ("Student".equals(mClass.getSimpleName())) {
			result = new StudentsFilter(mapParameters).filter((List<Student>) list);
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			result = new TeachersFilter(mapParameters).filter((List<Teacher>) list);
		} else if ("Team".equals(mClass.getSimpleName())) {
			result = new TeamsFilter(mapParameters).filter((List<Team>) list);
		}
		
		return trim(result);
	}
	
	private static Object trim(List result) {
		return (result != null && result.size() == 1) ? result.get(0) : result;
	}
}
