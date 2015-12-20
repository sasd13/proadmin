package ws.filter;

import java.util.Map;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.filter.member.AcademicLevelCriteria;
import com.sasd13.proadmin.core.filter.member.EmailCriteria;

public class StudentsFilter extends Filter<Student> {
	
	public StudentsFilter(Map<String, String[]> mapParameters) {
		super(mapParameters);
		
		for (String key : mapParameters.keySet()) {
			if ("academicLevel".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value.toUpperCase())));
				}
			}
			
			if ("email".equalsIgnoreCase(key)) {
				for (String value : mapParameters.get(key)) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				}
			}
		}
	}
}
