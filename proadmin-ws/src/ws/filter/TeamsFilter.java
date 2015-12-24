package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.filter.running.RunningCriteria;

public class TeamsFilter extends AbstractFilter<Team> {
	
	public TeamsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("running".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
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
