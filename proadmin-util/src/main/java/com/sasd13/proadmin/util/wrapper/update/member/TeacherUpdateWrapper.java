package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.ITeacher;

public class TeacherUpdateWrapper implements IUpdateWrapper<ITeacher> {

	private ITeacher iTeacher;
	private String intermediary;

	@Override
	public ITeacher getWrapped() {
		return iTeacher;
	}

	@Override
	public void setWrapped(ITeacher iTeacher) {
		this.iTeacher = iTeacher;
	}

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}
}
