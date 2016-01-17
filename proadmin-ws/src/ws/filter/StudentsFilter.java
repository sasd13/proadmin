package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.http.HttpFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.filter.member.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;

public class StudentsFilter extends HttpFilter<Student> {
	
	public StudentsFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		String key;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			key = entry.getKey();
			
			for (String value : entry.getValue()) {
				if ("number".equals(key)) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if ("academiclevel".equals(key)) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if ("email".equals(key)) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				}
			}
		}
	}
}
