package com.sasd13.proadmin.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "runningteams")
public class RunningTeam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783572108372933061L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_running")
	private Running running;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_team")
	private Team team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_academiclevel")
	private AcademicLevel academicLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Running getRunning() {
		return running;
	}

	public void setRunning(Running running) {
		this.running = running;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((running == null) ? 0 : running.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((academicLevel == null) ? 0 : academicLevel.hashCode());

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

		RunningTeam other = (RunningTeam) obj;

		if (running == null && other.running != null)
			return false;
		else if (!running.equals(other.running))
			return false;

		if (team == null && other.team != null)
			return false;
		else if (!team.equals(other.team))
			return false;

		if (academicLevel == null && other.academicLevel != null)
			return false;
		else if (!academicLevel.equals(other.academicLevel))
			return false;

		return true;
	}
}
