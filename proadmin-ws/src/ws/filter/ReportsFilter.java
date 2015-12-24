package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.filter.running.TeamCriteria;

public class ReportsFilter extends AbstractFilter<Report> {
	
	public ReportsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("team".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
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
