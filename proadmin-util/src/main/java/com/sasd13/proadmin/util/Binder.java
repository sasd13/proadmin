package com.sasd13.proadmin.util;

import java.util.List;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class Binder {

	public static void bind(Teacher target, Teacher source) {
		target.setId(source.getId());
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}

	public static void bind(Project target, Project source) {
		target.setId(source.getId());
		target.setAcademicLevel(source.getAcademicLevel());
		target.setCode(source.getCode());
		target.setTitle(source.getTitle());
		target.setDescription(source.getDescription());
	}

	public static void bind(Student target, Student source) {
		target.setId(source.getId());
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setAcademicLevel(source.getAcademicLevel());
	}

	public static void bind(Team target, Team source) {
		target.setId(source.getId());
		target.setCode(source.getCode());
	}

	public static void bind(StudentTeam target, StudentTeam source) {
		target.setId(source.getId());
	}

	public static void bind(Running target, Running source) {
		target.setId(source.getId());
		target.setYear(source.getYear());
		target.setTeacher(source.getTeacher());
		target.setProject(source.getProject());
	}

	public static void bind(RunningTeam target, RunningTeam source) {
		target.setId(source.getId());
	}

	public static void bind(Report target, Report source) {
		target.setId(source.getId());
		target.setNumber(source.getNumber());
		target.setMeetingDate(source.getMeetingDate());
		target.setSession(source.getSession());
		target.setComment(source.getComment());

		bind(target.getLeadEvaluation(), source.getLeadEvaluation());

		target.getIndividualEvaluations().clear();

		List<IndividualEvaluation> individualEvaluations = source.getIndividualEvaluations();
		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation(target);
			bind(individualEvaluationToAdd, individualEvaluation);
		}
	}

	public static void bind(LeadEvaluation target, LeadEvaluation source) {
		target.setId(source.getId());
		target.setPlanningMark(source.getPlanningMark());
		target.setPlanningComment(source.getPlanningComment());
		target.setCommunicationMark(source.getCommunicationMark());
		target.setCommunicationComment(source.getCommunicationComment());
		target.setStudent(source.getStudent());
	}

	public static void bind(IndividualEvaluation target, IndividualEvaluation source) {
		target.setId(source.getId());
		target.setMark(source.getMark());
		target.setStudent(source.getStudent());
	}
}
