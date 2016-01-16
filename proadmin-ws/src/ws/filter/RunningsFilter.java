package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.filter.running.ProjectCriteria;
import com.sasd13.proadmin.core.filter.running.TeacherCriteria;
import com.sasd13.proadmin.core.filter.running.YearCriteria;

public class RunningsFilter extends AbstractFilter<Running> {
	
	public RunningsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("year".equals(key)) {
					try {
						multiAndCriteria.addCriteria(new YearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if ("teacher".equals(key)) {
					try {
						multiAndCriteria.addCriteria(new TeacherCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if ("project".equals(key)) {
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
