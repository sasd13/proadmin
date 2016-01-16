package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.filter.running.TeamCriteria;

public class ReportsFilter extends AbstractFilter<Report> {
	
	public ReportsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("team".equals(key)) {
					try {
						multiAndCriteria.addCriteria(new TeamCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
