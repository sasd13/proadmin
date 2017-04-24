package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;

public class TeacherUpdateAdapterI2B implements IAdapter<TeacherBean, TeacherUpdate> {

	@Override
	public TeacherUpdate adapt(TeacherBean s) {
		TeacherUpdate t = new TeacherUpdate();

		t.setIntermediary(s.getId().getId());

		Teacher teacher = new Teacher();
		teacher.setIntermediary(s.getCoreInfo().getIntermediary());
		teacher.setFirstName(s.getCoreInfo().getFirstName());
		teacher.setLastName(s.getCoreInfo().getLastName());
		teacher.setEmail(s.getCoreInfo().getEmail());
		t.setWrapped(teacher);

		return t;
	}
}
