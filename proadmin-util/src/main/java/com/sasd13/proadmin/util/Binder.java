package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;

public class Binder {
	
	public static void bind(Teacher target, Teacher source) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
	}

	public static void bind(Project target, Project source) {
		target.setAcademicLevel(source.getAcademicLevel());
		target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
    }
	
	public static void bind(Student target, Student source) {
		target.setNumber(source.getNumber());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setAcademicLevel(source.getAcademicLevel());
    }

    public static void bind(Team target, Team source) {
        target.setCode(source.getCode());
    }
    
    public static void bind(Running target, Running source) {
        target.setYear(source.getYear());
        target.setTeacher(source.getTeacher());
        target.setProject(source.getProject());
    }
    
    public static void bind(Report target, Report source) {
        target.setMeetingDate(source.getMeetingDate());
        target.setSessionNumber(source.getSessionNumber());
        target.setComment(source.getComment());
    }
    
    public static void bind(LeadEvaluation target, LeadEvaluation source) {
    	target.setPlanningMark(source.getPlanningMark());
    	target.setPlanningComment(source.getPlanningComment());
    	target.setCommunicationMark(source.getCommunicationMark());
    	target.setCommunicationComment(source.getCommunicationComment());
    	target.setStudent(source.getStudent());
    }
    
    public static void bind(IndividualEvaluation target, IndividualEvaluation source) {
    	target.setMark(source.getMark());
    	target.setStudent(source.getStudent());
    }
}
