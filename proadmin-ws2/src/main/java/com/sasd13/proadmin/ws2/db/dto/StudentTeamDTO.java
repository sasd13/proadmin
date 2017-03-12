package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.member.StudentTeam;

@Entity
@Table(name = "studentteams")
public class StudentTeamDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038936998479170893L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_studentteams_id")
	@SequenceGenerator(name = "seq_studentteams_id", sequenceName = "seq_studentteams_id")
	@Column(name = "_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_student")
	private StudentDTO student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_team")
	private TeamDTO team;

	public StudentTeamDTO() {
	}

	public StudentTeamDTO(StudentTeam studentTeam) {
		student = new StudentDTO(studentTeam.getStudent());
		team = new TeamDTO(studentTeam.getTeam());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());

		return result;
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
