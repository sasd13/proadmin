package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

public class TeacherDTOAdapter implements IAdapter<TeacherDTO, Teacher> {

	@Override
	public Teacher adapt(TeacherDTO source) {
		Teacher target = new Teacher();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(TeacherDTO source, Teacher target) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}
}
