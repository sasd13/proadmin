package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.filter.member.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.FirstNameCriteria;
import com.sasd13.proadmin.core.filter.member.LastNameCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;
import com.sasd13.proadmin.core.util.URLParameter;

public class StudentFilter extends URLParameterFilter<Student> {
	
	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (URLParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if (URLParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if (URLParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Student>(value));
				} else if (URLParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Student>(value));
				} else if (URLParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				}
			}
		}
	}
}
