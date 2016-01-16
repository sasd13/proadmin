package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.filter.running.CodeCriteria;
import com.sasd13.proadmin.core.filter.running.RunningCriteria;

public class TeamsFilter extends AbstractFilter<Team> {
	
	public TeamsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("code".equals(key)) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if ("running".equals(key)) {
					try {
						multiAndCriteria.addCriteria(new RunningCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
