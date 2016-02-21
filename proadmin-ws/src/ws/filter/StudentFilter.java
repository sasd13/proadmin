package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.filter.member.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.FirstNameCriteria;
import com.sasd13.proadmin.core.filter.member.LastNameCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;
import com.sasd13.proadmin.core.util.EnumURLParameter;

public class StudentFilter extends URLParameterFilter<Student> {
	
	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumURLParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if (EnumURLParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if (EnumURLParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Student>(value));
				} else if (EnumURLParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Student>(value));
				} else if (EnumURLParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				}
			}
		}
	}
}
