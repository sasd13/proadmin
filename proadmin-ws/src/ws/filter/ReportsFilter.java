package ws.filter;

import java.util.Map;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.filter.running.TeamCriteria;

public class ReportsFilter extends Filter<Report> {

	public ReportsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("team".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					multiAndCriteria.addCriteria(new TeamCriteria(Long.parseLong(value)));
				}
			}
		}
	}
}
