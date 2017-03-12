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

import com.sasd13.proadmin.bean.running.Running;

@Entity
@Table(name = "runnings")
public class RunningDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579626282532440081L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_runnings_id")
	@SequenceGenerator(name = "seq_runnings_id", sequenceName = "seq_runnings_id")
	@Column(name = "_id")
	private long id;

	@Column(name = "_year")
	private int year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_project")
	private ProjectDTO project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_teacher")
	private TeacherDTO teacher;

	public RunningDTO() {
	}

	public RunningDTO(Running running) {
		year = running.getYear();
		project = new ProjectDTO(running.getProject());
		teacher = new TeacherDTO(running.getTeacher());
	}

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

	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}

	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
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

		RunningDTO other = (RunningDTO) obj;

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
