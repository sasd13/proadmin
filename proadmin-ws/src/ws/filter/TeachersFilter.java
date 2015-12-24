package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;

public class TeachersFilter extends AbstractFilter<Teacher> {
	
	public TeachersFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("email".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
