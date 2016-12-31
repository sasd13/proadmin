package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;

public interface IStudentUpdateWrapper extends IUpdateWrapper<Student> {

	String getNumber();

	void setNumber(String number);
}
