package com.sasd13.proadmin.core.bean.running.handler;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.Team;

public class StudentTeamHandler {
	
	public static void setStudenToStudentTeam(Student student, StudentTeam studentTeam) {
		Student studentTarget = studentTeam.getStudent();
		
		studentTarget.setId(student.getId());
		studentTarget.setAcademicLevel(student.getAcademicLevel());
		studentTarget.setNumber(student.getNumber());
		studentTarget.setFirstName(student.getFirstName());
		studentTarget.setLastName(student.getLastName());
		studentTarget.setEmail(student.getEmail());
	}
	
	public static void setTeamToStudentTeam(Team team, StudentTeam studentTeam) {
		Team teamTarget = studentTeam.getTeam();
		
		teamTarget.setId(team.getId());
		teamTarget.setCode(team.getCode());
		teamTarget.setRunning(team.getRunning());
		TeamHandler.setReportsToTeam(team.getReports(), teamTarget);
	}
}
