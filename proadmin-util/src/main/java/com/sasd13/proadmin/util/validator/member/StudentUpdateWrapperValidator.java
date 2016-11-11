package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;

public class StudentUpdateWrapperValidator implements IValidator<IUpdateWrapper<Student>> {

	private StudentValidator studentValidator;

	public StudentUpdateWrapperValidator() {
		studentValidator = new StudentValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Student> updateWrapper) throws ValidatorException {
		IStudentUpdateWrapper studentUpdateWrapper = (IStudentUpdateWrapper) updateWrapper;

		if (studentUpdateWrapper == null) {
			throw new ValidatorException("StudentUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(studentUpdateWrapper.getNumber())) {
			throw new ValidatorException("StudentUpdateWrapper : number is not valid");
		}

		studentValidator.validate(studentUpdateWrapper.getWrapped());
	}
}
