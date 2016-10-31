package com.sasd13.proadmin.util.validator.project;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectValidator implements IValidator<Project> {

	@Override
	public void validate(Project project) throws ValidatorException {
		if (project == null) {
			throw new ValidatorException("Project is not valid");
		}

		if (StringUtils.isBlank(project.getCode())) {
			throw new ValidatorException("Project: code is not valid");
		}

		if (StringUtils.isBlank(project.getTitle())) {
			throw new ValidatorException("Project: title is not valid");
		}

		if (StringUtils.isBlank(project.getDescription())) {
			throw new ValidatorException("Project: description is not valid");
		}

		if (project.getDateCreation() == null) {
			throw new ValidatorException("Project: dateCreation is not valid");
		}
	}
}
