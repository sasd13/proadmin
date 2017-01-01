package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.IStudentTeamDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.StudentTeamUpdateWrapper;

/**
 * Created by Samir on 12/03/2016.
 */
public class StudentTeamBusiness implements IBusiness<StudentTeam> {

	@Autowired
	private IStudentTeamDAO dao;

	private Map<String, String[]> parameters;

	public StudentTeamBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(StudentTeam studentTeam) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.STUDENT.getName(), new String[] { studentTeam.getStudent().getNumber() });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { studentTeam.getTeam().getNumber() });

		if (!dao.select(parameters).isEmpty()) {
			throw new BusinessException("StudentTeam already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<StudentTeam> updateWrapper) throws BusinessException {
		StudentTeamUpdateWrapper studentTeamUpdateWrapper = (StudentTeamUpdateWrapper) updateWrapper;

		if (!studentTeamUpdateWrapper.getStudentNumber().equals(studentTeamUpdateWrapper.getWrapped().getStudent().getNumber()) 
				|| !studentTeamUpdateWrapper.getTeamNumber().equals(studentTeamUpdateWrapper.getWrapped().getTeam().getNumber())) {
			verify(studentTeamUpdateWrapper.getWrapped());
		}
	}
}
