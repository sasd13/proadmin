package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.filter.project.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.project.CodeCriteria;

public class ProjectsFilter extends AbstractFilter<Project> {
	
	public ProjectsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String value;
		
		for (String key : parameters.keySet()) {
			value = parameters.get(key)[0];
			
			if ("code".equals(key)) {
				multiAndCriteria.addCriteria(new CodeCriteria(value));
			} else if ("academiclevel".equals(key)) {
				try {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value.toUpperCase())));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
