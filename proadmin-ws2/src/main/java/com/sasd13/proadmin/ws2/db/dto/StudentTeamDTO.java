package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.IStudentTeamDAO;

@Entity
@Table(name = IStudentTeamDAO.TABLE)
public class StudentTeamDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038936998479170893L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_studentteams_id")
	@SequenceGenerator(name = "seq_studentteams_id", sequenceName = "seq_studentteams_id")
	@Column(name = IStudentTeamDAO.COLUMN_ID)
	private long id;

	@Column(name = IStudentTeamDAO.COLUMN_STUDENT)
	private Student student;

	@Column(name = IStudentTeamDAO.COLUMN_TEAM)
	private Team team;

	public StudentTeamDTO() {
	}

	public StudentTeamDTO(StudentTeam studentTeam) {
		student = studentTeam.getStudent();
		team = studentTeam.getTeam();
	}

	public long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		StudentTeamDTO other = (StudentTeamDTO) obj;

		if (student == null && other.student != null)
			return false;
		else if (!student.equals(other.student))
			return false;

		if (team == null && other.team != null)
			return false;
		else if (!team.equals(other.team))
			return false;

		return true;
	}
}
