package com.sasd13.proadmin.backend.util.adapter.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.Student;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

public class StudentAdapterI2M implements IAdapter<StudentBean, Student> {

	@Override
	public Student adapt(StudentBean s) {
		Student t = new Student();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setIntermediary(s.getCoreInfo().getIntermediary());
		t.setFirstName(s.getCoreInfo().getFirstName());
		t.setLastName(s.getCoreInfo().getLastName());
		t.setEmail(s.getCoreInfo().getEmail());

		return t;
	}
}
