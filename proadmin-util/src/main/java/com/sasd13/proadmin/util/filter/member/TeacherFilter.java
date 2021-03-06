package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.util.EnumCriteria;

public class TeacherFilter extends AndFilter<ITeacher> {

	public TeacherFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<ITeacher> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberIntermediaryCriteria<ITeacher>(value));
				} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<ITeacher>(value));
				} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<ITeacher>(value));
				} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<ITeacher>(value));
				}
			}
		}
	}
}
