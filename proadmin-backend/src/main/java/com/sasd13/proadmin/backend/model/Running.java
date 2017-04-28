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
@Table(name = "runnings")
public class Running implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579626282532440081L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_year")
	private int year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_project")
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_teacher")
	private Teacher teacher;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + year;
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());

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

		Running other = (Running) obj;

		if (year != other.year)
			return false;

		if (project == null && other.project != null)
			return false;
		else if (!project.equals(other.project))
			return false;

		if (teacher == null && other.teacher != null)
			return false;
		else if (!teacher.equals(other.teacher))
			return false;

		return true;
	}
}
