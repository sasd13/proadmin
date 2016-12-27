package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

public class StudentBusiness implements IBusiness<Student> {

	private Map<String, String[]> parameters;

	public StudentBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, Student member) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { member.getNumber() });

		if (!dao.getSession(Student.class).select(parameters).isEmpty()) {
			throw new BusinessException("Student already exist");
		}
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Student> updateWrapper) throws BusinessException {
		StudentUpdateWrapper studentUpdateWrapper = (StudentUpdateWrapper) updateWrapper;

		if (!studentUpdateWrapper.getNumber().equals(studentUpdateWrapper.getWrapped().getNumber())) {
			verify(dao, studentUpdateWrapper.getWrapped());
		}
	}
}
