package com.sasd13.proadmin.util.dao.validator;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.validator.IValidator;
import com.sasd13.javaex.dao.validator.ValidatorException;
import com.sasd13.proadmin.bean.AcademicLevel;

public class AcademicLevelValidator implements IValidator<AcademicLevel> {

	@Override
	public void validate(AcademicLevel academicLevel) throws ValidatorException {
		if (academicLevel == null) {
			throw new ValidatorException("AcademicLevel: null");
		}

		if (StringUtils.isBlank(academicLevel.getCode())) {
			throw new ValidatorException("AcademicLevel: code is not valid");
		}
	}
}
