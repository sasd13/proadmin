package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.Teacher;

public class TeacherValidator implements IValidator<Teacher> {

	@Override
	public void validate(Teacher teacher) throws ValidatorException {
		if (teacher == null) {
			throw new ValidatorException("Teacher is not valid");
		}

		if (StringUtils.isBlank(teacher.getNumber())) {
			throw new ValidatorException("Teacher : number is not valid");
		}

		if (StringUtils.isBlank(teacher.getFirstName())) {
			throw new ValidatorException("Teacher : firstName is not valid");
		}

		if (StringUtils.isBlank(teacher.getLastName())) {
			throw new ValidatorException("Teacher : lastName is not valid");
		}

		if (StringUtils.isBlank(teacher.getEmail())) {
			throw new ValidatorException("Teacher : email is not valid");
		}
	}
}
