package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;

public class StudentAdapterD2B implements IAdapter<StudentDTO, Student> {

	@Override
	public Student adapt(StudentDTO s) {
		Student t = new Student();

		t.setId(s.getId());
		t.setIntermediary(s.getIntermediary());
		t.setFirstName(s.getFirstName());
		t.setLastName(s.getLastName());
		t.setEmail(s.getEmail());

		return t;
	}
}
