package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherUpdateWrapper extends IUpdateWrapper<Teacher> {

	String getNumber();

	void setNumber(String number);
}
