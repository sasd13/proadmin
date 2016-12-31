package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamUpdateWrapper extends IUpdateWrapper<StudentTeam> {

	String getStudentNumber();

	void setStudentNumber(String studentNumber);

	String getTeamNumber();

	void setTeamNumber(String teamNumber);
}
