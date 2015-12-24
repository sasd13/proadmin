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
	
	public static List filter(List list, Map<String, String[]> mapParameters, Class mClass) {
		if ("Teacher".equals(mClass.getSimpleName())) {
			return new TeachersFilter(mapParameters).filter((List<Teacher>) list);
		} else if ("Project".equals(mClass.getSimpleName())) {
			return new ProjectsFilter(mapParameters).filter((List<Project>) list);
		} else if ("Running".equals(mClass.getSimpleName())) {
			return new RunningsFilter(mapParameters).filter((List<Running>) list);
		} else if ("Team".equals(mClass.getSimpleName())) {
			return new TeamsFilter(mapParameters).filter((List<Team>) list);
		} else if ("Student".equals(mClass.getSimpleName())) {
			return new StudentsFilter(mapParameters).filter((List<Student>) list);
		} else if ("Report".equals(mClass.getSimpleName())) {
			return new ReportsFilter(mapParameters).filter((List<Report>) list);
		} else {
			return null;
		}
	}
}
