package com.sasd13.proadmin.core.filter;

import java.util.Map;

import com.sasd13.javaex.util.ParameterFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.filter.member.StudentFilter;
import com.sasd13.proadmin.core.filter.member.TeacherFilter;
import com.sasd13.proadmin.core.filter.project.ProjectFilter;
import com.sasd13.proadmin.core.filter.running.ReportFilter;
import com.sasd13.proadmin.core.filter.running.RunningFilter;
import com.sasd13.proadmin.core.filter.running.TeamFilter;

public class FilterFactory {
	
	public static <T> ParameterFilter<T> make(Class<T> mClass, Map<String, String[]> parameters) throws FilterException {
		if (Teacher.class.equals(mClass)) {
			return (ParameterFilter<T>) new TeacherFilter(parameters);
		} else if (Project.class.equals(mClass)) {
			return (ParameterFilter<T>) new ProjectFilter(parameters);
		} else if (Running.class.equals(mClass)) {
			return (ParameterFilter<T>) new RunningFilter(parameters);
		} else if (Team.class.equals(mClass)) {
			return (ParameterFilter<T>) new TeamFilter(parameters);
		} else if (Student.class.equals(mClass)) {
			return (ParameterFilter<T>) new StudentFilter(parameters);
		} else if (Report.class.equals(mClass)) {
			return (ParameterFilter<T>) new ReportFilter(parameters);
		} else {
			throw new FilterException("Class '" + mClass.getName() + "' has no parameter filter");
		}
	}
}
