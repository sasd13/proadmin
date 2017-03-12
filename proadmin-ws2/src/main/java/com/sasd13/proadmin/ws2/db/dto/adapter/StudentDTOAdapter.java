package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;

public class StudentDTOAdapter implements IAdapter<StudentDTO, Student> {

	@Override
	public Student adapt(StudentDTO source) {
		Student target = new Student();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(StudentDTO source, Student target) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}
}
