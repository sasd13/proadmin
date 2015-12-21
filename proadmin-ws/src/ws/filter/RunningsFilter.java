package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.Filter;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.filter.running.ProjectCriteria;
import com.sasd13.proadmin.core.filter.running.TeacherCriteria;
import com.sasd13.proadmin.core.filter.running.YearCriteria;

public class RunningsFilter extends Filter<Running> {
	
	public RunningsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("year".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					try {
						multiAndCriteria.addCriteria(new YearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			
			if ("teacher".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					try {
						multiAndCriteria.addCriteria(new TeacherCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			
			if ("project".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
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
