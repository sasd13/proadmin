package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class YearCriteria implements Criteria<Running> {
	
	private int year;
	
	public YearCriteria(int year) {
		this.year = year;
	}
	
	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();
		
		for (Running running : list) {
			if (running.getYear() == year) {
				results.add(running);
			}
		}
		
		return results;
	}
}
