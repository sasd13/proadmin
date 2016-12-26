package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.Student;

public class StudentValidator implements IValidator<Student> {

	@Override
	public void validate(Student student) throws ValidatorException {
		if (student == null) {
			throw new ValidatorException("Student is not valid");
		}

		if (StringUtils.isBlank(student.getNumber())) {
			throw new ValidatorException("Student : number is not valid");
		}

		if (StringUtils.isBlank(student.getFirstName())) {
			throw new ValidatorException("Student : firstName is not valid");
		}

		if (StringUtils.isBlank(student.getLastName())) {
			throw new ValidatorException("Student : lastName is not valid");
		}

		if (StringUtils.isBlank(student.getEmail())) {
			throw new ValidatorException("Student : email is not valid");
		}
	}
}
