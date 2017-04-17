package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.error.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;

public class TeacherBusiness implements IBusiness<Teacher> {

	@Autowired
	private ITeacherDAO dao;

	private Map<String, String[]> parameters;

	public TeacherBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(Teacher member) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { member.getNumber() });

		if (!dao.read(parameters).isEmpty()) {
			throw new BusinessException("Teacher already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<Teacher> updateWrapper) throws BusinessException {
		TeacherUpdateWrapper teacherUpdateWrapper = (TeacherUpdateWrapper) updateWrapper;

		if (!teacherUpdateWrapper.getNumber().equals(teacherUpdateWrapper.getWrapped().getNumber())) {
			verify(teacherUpdateWrapper.getWrapped());
		}
	}
}
