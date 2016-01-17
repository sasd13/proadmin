package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.HttpFilter;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.filter.project.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.project.CodeCriteria;

public class ProjectsFilter extends HttpFilter<Project> {
	
	public ProjectsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("code".equals(key)) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if ("academiclevel".equals(key)) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				}
			}
		}
	}
}
