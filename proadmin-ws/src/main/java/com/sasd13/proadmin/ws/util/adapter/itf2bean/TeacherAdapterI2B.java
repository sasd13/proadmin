package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.ws.bean.Teacher;

public class TeacherAdapterI2B implements IAdapter<TeacherBean, Teacher> {

	@Override
	public Teacher adapt(TeacherBean s) {
		Teacher t = new Teacher();

		t.setIntermediary(s.getCoreInfo().getIntermediary());
		t.setFirstName(s.getCoreInfo().getFirstName());
		t.setLastName(s.getCoreInfo().getLastName());
		t.setEmail(s.getCoreInfo().getEmail());

		return t;
	}
}
