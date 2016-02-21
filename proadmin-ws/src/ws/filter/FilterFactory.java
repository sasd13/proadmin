package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.filter.FilterException;

public class FilterFactory {
	
	public static <T> URLParameterFilter<T> make(Class<T> mClass, Map<String, String[]> parameters) throws FilterException {
		if (Teacher.class.equals(mClass)) {
			return (URLParameterFilter<T>) new TeacherFilter(parameters);
		} else if (Project.class.equals(mClass)) {
			return (URLParameterFilter<T>) new ProjectFilter(parameters);
		} else if (Running.class.equals(mClass)) {
			return (URLParameterFilter<T>) new RunningFilter(parameters);
		} else if (Team.class.equals(mClass)) {
			return (URLParameterFilter<T>) new TeamFilter(parameters);
		} else if (Student.class.equals(mClass)) {
			return (URLParameterFilter<T>) new StudentFilter(parameters);
		} else if (Report.class.equals(mClass)) {
			return (URLParameterFilter<T>) new ReportFilter(parameters);
		} else {
			throw new FilterException("Class '" + mClass.getName() + "' has no parameter filter");
		}
	}
}
