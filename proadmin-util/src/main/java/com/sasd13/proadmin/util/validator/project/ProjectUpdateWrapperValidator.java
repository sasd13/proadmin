package com.sasd13.proadmin.util.validator.project;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public class ProjectUpdateWrapperValidator implements IValidator<IUpdateWrapper<Project>> {

	private ProjectValidator projectValidator;

	public ProjectUpdateWrapperValidator() {
		projectValidator = new ProjectValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Project> updateWrapper) throws ValidatorException {
		ProjectUpdateWrapper projectUpdateWrapper = (ProjectUpdateWrapper) updateWrapper;

		if (projectUpdateWrapper == null) {
			throw new ValidatorException("ProjectUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(projectUpdateWrapper.getCode())) {
			throw new ValidatorException("ProjectUpdateWrapper : code is not valid");
		}

		projectValidator.validate(projectUpdateWrapper.getWrapped());
	}
}
