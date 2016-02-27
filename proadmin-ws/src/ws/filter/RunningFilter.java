package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.filter.running.ProjectCriteria;
import com.sasd13.proadmin.core.filter.running.TeacherCriteria;
import com.sasd13.proadmin.core.filter.running.YearCriteria;
import com.sasd13.proadmin.core.util.URLParameter;

public class RunningFilter extends URLParameterFilter<Running> {
	
	public RunningFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (URLParameter.YEAR.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new YearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (URLParameter.TEACHER.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new TeacherCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (URLParameter.PROJECT.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ProjectCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
