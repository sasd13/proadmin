package com.sasd13.proadmin.backend.util.adapter.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherAdapterI2M implements IAdapter<TeacherBean, Teacher> {

	@Override
	public Teacher adapt(TeacherBean s) {
		Teacher t = new Teacher();

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
