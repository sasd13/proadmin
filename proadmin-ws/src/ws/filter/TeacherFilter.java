package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.FirstNameCriteria;
import com.sasd13.proadmin.core.filter.member.LastNameCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;
import com.sasd13.proadmin.core.util.EnumURLParameter;

public class TeacherFilter extends URLParameterFilter<Teacher> {
	
	public TeacherFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumURLParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Teacher>(value));
				} else if (EnumURLParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Teacher>(value));
				} else if (EnumURLParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Teacher>(value));
				} else if (EnumURLParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
