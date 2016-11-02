package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;

public class TeamFilter extends AndFilter<Team> {

	public TeamFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Team> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.CODE.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new TeamCodeCriteria(value));
				}
			}
		}
	}
}
