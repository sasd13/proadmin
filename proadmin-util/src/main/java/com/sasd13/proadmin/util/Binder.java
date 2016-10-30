package com.sasd13.proadmin.util;

import java.util.List;

import com.sasd13.proadmin.bean.AcademicLevel;
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

	public static void bind(AcademicLevel target, AcademicLevel source) {
		// Do nothing
	}

	public static void bind(Project target, Project source) {
		target.setCode(source.getCode());
		target.setDateCreation(source.getDateCreation());
		target.setTitle(source.getTitle());
		target.setDescription(source.getDescription());
	}

	public static void bind(Teacher target, Teacher source) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}

	public static void bind(Student target, Student source) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}

	public static void bind(Team target, Team source) {
		target.setNumber(source.getNumber());
	}

	public static void bind(StudentTeam target, StudentTeam source) {
		// Do nothing
	}

	public static void bind(Running target, Running source) {
		target.setYear(source.getYear());
		target.setTeacher(source.getTeacher());
	}

	public static void bind(RunningTeam target, RunningTeam source) {
		target.setAcademicLevel(source.getAcademicLevel());
	}

	public static void bind(Report target, Report source) {
		target.setNumber(source.getNumber());
		target.setDateMeeting(source.getDateMeeting());
		target.setSession(source.getSession());
		target.setComment(source.getComment());

		bindLeadEvaluation(target, source);
		bindIndividualEvaluations(target, source);
	}

	private static void bindLeadEvaluation(Report target, Report source) {
		bind(target.getLeadEvaluation(), source.getLeadEvaluation());
	}

	public static void bind(LeadEvaluation target, LeadEvaluation source) {
		target.setPlanningMark(source.getPlanningMark());
		target.setPlanningComment(source.getPlanningComment());
		target.setCommunicationMark(source.getCommunicationMark());
		target.setCommunicationComment(source.getCommunicationComment());
		target.setStudent(source.getStudent());
	}

	private static void bindIndividualEvaluations(Report target, Report source) {
		target.getIndividualEvaluations().clear();

		List<IndividualEvaluation> individualEvaluations = source.getIndividualEvaluations();
		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation(target);
			bind(individualEvaluationToAdd, individualEvaluation);
		}
	}

	public static void bind(IndividualEvaluation target, IndividualEvaluation source) {
		target.setMark(source.getMark());
		target.setStudent(source.getStudent());
	}
}
