package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.filter.running.CodeCriteria;
import com.sasd13.proadmin.core.filter.running.RunningCriteria;
import com.sasd13.proadmin.core.util.URLParameter;

public class TeamFilter extends URLParameterFilter<Team> {
	
	public TeamFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (URLParameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (URLParameter.RUNNING.getName().equals(entry.getKey())) {
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
