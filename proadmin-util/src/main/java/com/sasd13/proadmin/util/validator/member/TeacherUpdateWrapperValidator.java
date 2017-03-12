package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public class TeacherUpdateWrapperValidator implements IValidator<IUpdateWrapper<Teacher>> {

	private TeacherValidator teacherValidator;

	public TeacherUpdateWrapperValidator() {
		teacherValidator = new TeacherValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Teacher> updateWrapper) throws ValidatorException {
		TeacherUpdateWrapper teacherUpdateWrapper = (TeacherUpdateWrapper) updateWrapper;

		if (teacherUpdateWrapper == null) {
			throw new ValidatorException("TeacherUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(teacherUpdateWrapper.getNumber())) {
			throw new ValidatorException("TeacherUpdateWrapper : number is not valid");
		}

		teacherValidator.validate(teacherUpdateWrapper.getWrapped());
	}
}
