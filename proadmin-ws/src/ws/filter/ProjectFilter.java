package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.filter.project.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.project.CodeCriteria;
import com.sasd13.proadmin.core.filter.project.TitleCriteria;
import com.sasd13.proadmin.core.util.EnumURLParameter;

public class ProjectFilter extends URLParameterFilter<Project> {
	
	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumURLParameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (EnumURLParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if (EnumURLParameter.TITLE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new TitleCriteria(value));
				}
			}
		}
	}
}
