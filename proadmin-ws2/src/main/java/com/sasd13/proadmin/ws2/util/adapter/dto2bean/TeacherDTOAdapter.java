package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws2.dao.dto.TeacherDTO;

public class TeacherDTOAdapter implements IAdapter<TeacherDTO, Teacher> {

	@Override
	public Teacher adapt(TeacherDTO source) {
		Teacher target = new Teacher();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(TeacherDTO source, Teacher target) {
		target.setIntermediary(source.getIntermediary());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}
}
