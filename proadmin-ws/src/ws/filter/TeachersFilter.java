package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;

public class TeachersFilter extends AbstractFilter<Teacher> {
	
	public TeachersFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("number".equals(key)) {
					multiAndCriteria.addCriteria(new NumberCriteria<Teacher>(value));
				} else if ("email".equals(key)) {
					multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
