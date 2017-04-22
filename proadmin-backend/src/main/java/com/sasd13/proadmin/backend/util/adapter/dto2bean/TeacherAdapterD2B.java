package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.dao.dto.TeacherDTO;

public class TeacherAdapterD2B implements IAdapter<TeacherDTO, Teacher> {

	@Override
	public Teacher adapt(TeacherDTO s) {
		Teacher t = new Teacher();

		t.setId(s.getId());
		t.setIntermediary(s.getIntermediary());
		t.setFirstName(s.getFirstName());
		t.setLastName(s.getLastName());
		t.setEmail(s.getEmail());

		return t;
	}
}
