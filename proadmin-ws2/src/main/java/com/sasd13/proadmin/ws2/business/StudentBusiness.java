package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.error.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IStudentDAO;

public class StudentBusiness implements IBusiness<Student> {

	@Autowired
	private IStudentDAO dao;

	private Map<String, String[]> parameters;

	public StudentBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(Student member) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { member.getNumber() });

		if (!dao.read(parameters).isEmpty()) {
			throw new BusinessException("Student already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<Student> updateWrapper) throws BusinessException {
		StudentUpdateWrapper studentUpdateWrapper = (StudentUpdateWrapper) updateWrapper;

		if (!studentUpdateWrapper.getNumber().equals(studentUpdateWrapper.getWrapped().getNumber())) {
			verify(studentUpdateWrapper.getWrapped());
		}
	}
}
