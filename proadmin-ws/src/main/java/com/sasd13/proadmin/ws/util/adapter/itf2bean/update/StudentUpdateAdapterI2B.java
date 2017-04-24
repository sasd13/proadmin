package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.update.StudentUpdate;

public class StudentUpdateAdapterI2B implements IAdapter<StudentBean, StudentUpdate> {

	@Override
	public StudentUpdate adapt(StudentBean s) {
		StudentUpdate t = new StudentUpdate();

		t.setIntermediary(s.getId().getId());

		Student student = new Student();
		student.setIntermediary(s.getCoreInfo().getIntermediary());
		student.setFirstName(s.getCoreInfo().getFirstName());
		student.setLastName(s.getCoreInfo().getLastName());
		student.setEmail(s.getCoreInfo().getEmail());
		t.setWrapped(student);

		return t;
	}
}
