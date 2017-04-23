package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.ws.bean.Student;

public class StudentAdapterI2B implements IAdapter<StudentBean, Student> {

	@Override
	public Student adapt(StudentBean s) {
		Student t = new Student();

		t.setIntermediary(s.getCoreInfo().getIntermediary());
		t.setFirstName(s.getCoreInfo().getFirstName());
		t.setLastName(s.getCoreInfo().getLastName());
		t.setEmail(s.getCoreInfo().getEmail());

		return t;
	}
}
