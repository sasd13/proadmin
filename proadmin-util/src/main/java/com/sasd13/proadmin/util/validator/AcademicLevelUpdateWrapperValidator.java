package com.sasd13.proadmin.util.validator;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.wrapper.update.IAcademicLevelUpdateWrapper;

public class AcademicLevelUpdateWrapperValidator implements IValidator<IUpdateWrapper<AcademicLevel>> {

	private AcademicLevelValidator academicLevelValidator;

	public AcademicLevelUpdateWrapperValidator() {
		academicLevelValidator = new AcademicLevelValidator();
	}

	@Override
	public void validate(IUpdateWrapper<AcademicLevel> updateWrapper) throws ValidatorException {
		IAcademicLevelUpdateWrapper academicLevelUpdateWrapper = (IAcademicLevelUpdateWrapper) updateWrapper;

		if (academicLevelUpdateWrapper == null) {
			throw new ValidatorException("AcademicLevelUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(academicLevelUpdateWrapper.getCode())) {
			throw new ValidatorException("AcademicLevelUpdateWrapper : code is not valid");
		}

		academicLevelValidator.validate(academicLevelUpdateWrapper.getWrapped());
	}
}
