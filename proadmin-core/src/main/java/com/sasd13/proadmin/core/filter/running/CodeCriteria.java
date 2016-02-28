package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Team;

public class CodeCriteria implements Criteria<Team> {
	
	private String code;
	
	public CodeCriteria(String code) {
		this.code = code;
	}
	
	@Override
	public List<Team> meetCriteria(List<Team> entities) {
		List<Team> result = new ArrayList<Team>();
		
		for (Team team : entities) {
			if (code.equalsIgnoreCase(team.getCode())) {
				result.add(team);
			}
		}
		
		return result;
	}
}
