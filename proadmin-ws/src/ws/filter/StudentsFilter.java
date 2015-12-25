package ws.filter;

import java.util.Map;

import com.sasd13.javaex.net.AbstractFilter;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.filter.member.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;
import com.sasd13.proadmin.core.filter.member.NumberCriteria;

public class StudentsFilter extends AbstractFilter<Student> {
	
	public StudentsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		String value;
		
		for (String key : mapParameters.keySet()) {
			value = mapParameters.get(key)[0];
			
			if ("number".equals(key)) {
				multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
			} else if ("academiclevel".equals(key)) {
				try {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value.toUpperCase())));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			} else if ("email".equals(key)) {
				multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
			}
		}
	}
}
