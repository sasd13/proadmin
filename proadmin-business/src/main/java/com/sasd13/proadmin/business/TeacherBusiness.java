package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public class TeacherBusiness implements IBusiness<Teacher> {

	private Map<String, String[]> parameters;

	public TeacherBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, Teacher member) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { member.getNumber() });

		if (!dao.getSession(Teacher.class).select(parameters).isEmpty()) {
			throw new BusinessException("Teacher already exist");
		}
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Teacher> updateWrapper) throws BusinessException {
		TeacherUpdateWrapper teacherUpdateWrapper = (TeacherUpdateWrapper) updateWrapper;

		if (!teacherUpdateWrapper.getNumber().equals(teacherUpdateWrapper.getWrapped().getNumber())) {
			verify(dao, teacherUpdateWrapper.getWrapped());
		}
	}
}
