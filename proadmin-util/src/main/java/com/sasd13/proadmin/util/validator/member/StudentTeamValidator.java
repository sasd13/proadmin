package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamValidator implements IValidator<StudentTeam> {

	@Override
	public void validate(StudentTeam studentTeam) throws ValidatorException {
		if (studentTeam == null) {
			throw new ValidatorException("StudentTeam is not valid");
		}

		if (studentTeam.getStudent() == null || StringUtils.isBlank(studentTeam.getStudent().getNumber())) {
			throw new ValidatorException("StudentTeam : student number is not valid");
		}

		if (studentTeam.getTeam() == null || StringUtils.isBlank(studentTeam.getTeam().getNumber())) {
			throw new ValidatorException("StudentTeam : team number is not valid");
		}
	}
}
