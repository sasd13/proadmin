package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.IRunningDAO;

@Entity
@Table(name = IRunningDAO.TABLE)
public class RunningDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579626282532440081L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_runnings_id")
	@SequenceGenerator(name = "seq_runnings_id", sequenceName = "seq_runnings_id")
	@Column(name = IRunningDAO.COLUMN_ID)
	private long id;

	@Column(name = IRunningDAO.COLUMN_YEAR)
	private int year;

	@Column(name = IRunningDAO.COLUMN_PROJECT)
	private Project project;

	@Column(name = IRunningDAO.COLUMN_TEACHER)
	private Teacher teacher;

	public RunningDTO() {
	}

	public RunningDTO(Running running) {
		year = running.getYear();
		project = running.getProject();
		teacher = running.getTeacher();
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

		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());

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

		RunningDTO other = (RunningDTO) obj;

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
