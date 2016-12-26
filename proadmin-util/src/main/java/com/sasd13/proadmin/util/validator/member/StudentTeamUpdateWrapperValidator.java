package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentTeamUpdateWrapper;

public class StudentTeamUpdateWrapperValidator implements IValidator<IUpdateWrapper<StudentTeam>> {

	private StudentTeamValidator studentTeamValidator;

	public StudentTeamUpdateWrapperValidator() {
		studentTeamValidator = new StudentTeamValidator();
	}

	@Override
	public void validate(IUpdateWrapper<StudentTeam> updateWrapper) throws ValidatorException {
		IStudentTeamUpdateWrapper studentTeamUpdateWrapper = (IStudentTeamUpdateWrapper) updateWrapper;

		if (studentTeamUpdateWrapper == null) {
			throw new ValidatorException("StudentTeamUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(studentTeamUpdateWrapper.getStudentNumber())) {
			throw new ValidatorException("StudentTeamUpdateWrapper : student number is not valid");
		}

		if (StringUtils.isBlank(studentTeamUpdateWrapper.getTeamNumber())) {
			throw new ValidatorException("StudentTeamUpdateWrapper : team number is not valid");
		}

		studentTeamValidator.validate(studentTeamUpdateWrapper.getWrapped());
	}
}
