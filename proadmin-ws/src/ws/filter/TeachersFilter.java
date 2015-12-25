package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;

public class TeachersFilter extends AbstractFilter<Teacher> {
	
	public TeachersFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		String value;
		
		for (String key : mapParameters.keySet()) {
			value = mapParameters.get(key)[0];
			
			if ("number".equals(key)) {
				multiAndCriteria.addCriteria(new NumberCriteria<Teacher>(value));
			} else if ("email".equals(key)) {
				multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
			}
		}
	}
}
