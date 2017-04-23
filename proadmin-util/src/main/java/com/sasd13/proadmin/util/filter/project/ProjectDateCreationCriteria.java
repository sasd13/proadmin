package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.project.IProject;

public class ProjectDateCreationCriteria implements Criteria<IProject> {

	public static final int YEAR_MIN = 1;
	public static final int MONTH_MIN = 1;
	public static final int MONTH_MAX = 12;

	private int year, month;

	public ProjectDateCreationCriteria(int year) {
		this(year, 0);
	}

	public ProjectDateCreationCriteria(int year, int month) {
		this.year = year;
		this.month = month;
	}

	@Override
	public List<IProject> meetCriteria(List<IProject> list) {
		List<IProject> results = new ArrayList<IProject>();

		if (year >= YEAR_MIN) {
			results = filterByYear(list);
		}

		if (month >= MONTH_MIN && month <= MONTH_MAX) {
			results = filterByMonth(results);
		}

		return results;
	}

	private List<IProject> filterByYear(List<IProject> list) {
		List<IProject> results = new ArrayList<IProject>();

		for (IProject item : list) {
			if (year == new DateTime(item.getDateCreation()).getYear()) {
				results.add(item);
			}
		}

		return results;
	}

	private List<IProject> filterByMonth(List<IProject> list) {
		List<IProject> results = new ArrayList<IProject>();

		for (IProject item : list) {
			if (month == new DateTime(item.getDateCreation()).getMonthOfYear()) {
				results.add(item);
			}
		}

		return results;
	}
}
