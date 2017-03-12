package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectDateCreationCriteria implements Criteria<Project> {

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
	public List<Project> meetCriteria(List<Project> list) {
		List<Project> results = new ArrayList<Project>();

		if (year >= YEAR_MIN) {
			results = filterByYear(list);
		}

		if (month >= MONTH_MIN && month <= MONTH_MAX) {
			results = filterByMonth(results);
		}

		return results;
	}

	private List<Project> filterByYear(List<Project> list) {
		List<Project> results = new ArrayList<Project>();

		for (Project project : list) {
			if (year == new DateTime(project.getDateCreation()).getYear()) {
				results.add(project);
			}
		}

		return results;
	}

	private List<Project> filterByMonth(List<Project> list) {
		List<Project> results = new ArrayList<Project>();

		for (Project project : list) {
			if (month == new DateTime(project.getDateCreation()).getMonthOfYear()) {
				results.add(project);
			}
		}

		return results;
	}
}
