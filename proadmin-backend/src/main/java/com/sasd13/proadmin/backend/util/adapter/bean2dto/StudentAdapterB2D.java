package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;

public class StudentAdapterB2D implements IAdapter<Student, StudentDTO> {

	@Override
	public StudentDTO adapt(Student s) {
		StudentDTO t = new StudentDTO();

		t.setId(s.getId());
		t.setIntermediary(s.getIntermediary());
		t.setFirstName(s.getFirstName());
		t.setLastName(s.getLastName());
		t.setEmail(s.getEmail());

		return t;
	}
}
