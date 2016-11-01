package com.sasd13.proadmin.util.validator.member;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.member.Team;

public class TeamValidator implements IValidator<Team> {

	@Override
	public void validate(Team team) throws ValidatorException {
		if (team == null) {
			throw new ValidatorException("Team is not valid");
		}

		if (StringUtils.isBlank(team.getNumber())) {
			throw new ValidatorException("Team : number is not valid");
		}
	}
}
