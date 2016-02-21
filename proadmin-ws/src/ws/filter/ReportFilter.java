package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.filter.running.TeamCriteria;
import com.sasd13.proadmin.core.filter.running.WeekCriteria;
import com.sasd13.proadmin.core.util.EnumURLParameter;

public class ReportFilter extends URLParameterFilter<Report> {
	
	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumURLParameter.WEEK.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new WeekCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (EnumURLParameter.TEAM.getName().equals(entry.getKey())) {
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
