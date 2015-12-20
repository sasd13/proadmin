package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.core.bean.member.AcademicMember;

public class EmailCriteria<T> implements Criteria<AcademicMember> {

	private String email;
	
	public EmailCriteria(String email) {
		this.email = email;
	}

	@Override
	public List<AcademicMember> meetCriteria(List<AcademicMember> entities) {
		List<AcademicMember> result = new ArrayList<AcademicMember>();
		
		for (AcademicMember academicMember : entities) {
			if (email.equals(academicMember.getEmail())) {
				result.add(academicMember);
			}
		}
		
		return result;
	}

}
