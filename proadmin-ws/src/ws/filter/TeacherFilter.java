package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.FirstNameCriteria;
import com.sasd13.proadmin.core.filter.member.LastNameCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;
import com.sasd13.proadmin.core.util.URLParameter;

public class TeacherFilter extends URLParameterFilter<Teacher> {
	
	public TeacherFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (URLParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Teacher>(value));
				} else if (URLParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Teacher>(value));
				} else if (URLParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Teacher>(value));
				} else if (URLParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
