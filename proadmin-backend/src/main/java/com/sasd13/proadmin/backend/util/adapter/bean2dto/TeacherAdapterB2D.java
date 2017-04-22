package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.dao.dto.TeacherDTO;

public class TeacherAdapterB2D implements IAdapter<Teacher, TeacherDTO> {

	@Override
	public TeacherDTO adapt(Teacher s) {
		TeacherDTO t = new TeacherDTO();

		t.setId(s.getId());
		t.setIntermediary(s.getIntermediary());
		t.setFirstName(s.getFirstName());
		t.setLastName(s.getLastName());
		t.setEmail(s.getEmail());

		return t;
	}
}
