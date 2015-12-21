package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.Filter;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.filter.project.AcademicLevelCriteria;

public class ProjectsFilter extends Filter<Project> {
	
	public ProjectsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("academiclevel".equals(key)) {
				for (String value : mapParameters.get(key)) {
					try {
						multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value.toUpperCase())));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
