package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.IProjectDAO;

@Entity
@Table(name = IProjectDAO.TABLE)
public class ProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080170606864758291L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projects_id")
	@SequenceGenerator(name = "seq_projects_id", sequenceName = "seq_projects_id")
	@Column(name = IProjectDAO.COLUMN_ID)
	private long id;

	@Column(name = IProjectDAO.COLUMN_CODE)
	private String code;

	@Column(name = IProjectDAO.COLUMN_DATECREATION)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateCreation;

	@Column(name = IProjectDAO.COLUMN_TITLE)
	private String title;

	@Column(name = IProjectDAO.COLUMN_DESCRIPTION)
	private String description;

	public ProjectDTO() {
	}

	public ProjectDTO(Project project) {
		code = project.getCode();
		dateCreation = new Timestamp(project.getDateCreation().getTime());
		title = project.getTitle();
		description = project.getDescription();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());

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

		ProjectDTO other = (ProjectDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		if (dateCreation == null && other.dateCreation != null)
			return false;
		else if (!dateCreation.equals(other.dateCreation))
			return false;

		if (title == null && other.title != null)
			return false;
		else if (!title.equals(other.title))
			return false;

		if (description == null && other.description != null)
			return false;
		else if (!description.equals(other.description))
			return false;

		return true;
	}
}
