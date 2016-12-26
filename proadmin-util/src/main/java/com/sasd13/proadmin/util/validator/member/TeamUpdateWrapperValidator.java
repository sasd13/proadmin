package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;

public class TeamUpdateWrapperValidator implements IValidator<IUpdateWrapper<Team>> {

	private TeamValidator teamValidator;

	public TeamUpdateWrapperValidator() {
		teamValidator = new TeamValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Team> updateWrapper) throws ValidatorException {
		ITeamUpdateWrapper teamUpdateWrapper = (ITeamUpdateWrapper) updateWrapper;

		if (teamUpdateWrapper == null) {
			throw new ValidatorException("TeamUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(teamUpdateWrapper.getNumber())) {
			throw new ValidatorException("TeamUpdateWrapper : number is not valid");
		}

		teamValidator.validate(teamUpdateWrapper.getWrapped());
	}
}
