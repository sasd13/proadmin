package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.ws2.dao.dto.StudentDTO;

public class StudentDTOAdapter implements IAdapter<StudentDTO, Student> {

	@Override
	public Student adapt(StudentDTO source) {
		Student target = new Student();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(StudentDTO source, Student target) {
		target.setIntermediary(source.getIntermediary());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}
}
