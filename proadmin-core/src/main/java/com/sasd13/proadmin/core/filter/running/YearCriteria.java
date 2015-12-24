package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Running;

public class YearCriteria implements Criteria<Running> {
	
	private int year;
	
	public YearCriteria(int year) {
		this.year = year;
	}
	
	@Override
	public List<Running> meetCriteria(List<Running> entities) {
		List<Running> result = new ArrayList<Running>();
		
		for (Running running : entities) {
			if (year == running.getYear()) {
				result.add(running);
			}
		}
		
		return result;
	}
	
}
