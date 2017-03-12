package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

@Entity
@Table(name = "projects")
public class ProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080170606864758291L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projects_id")
	@SequenceGenerator(name = "seq_projects_id", sequenceName = "seq_projects_id")
	@Column(name = "_id")
	private long id;

	@Column(name = "_code")
	private String code;

	@Column(name = "_datecreation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column(name = "_title")
	private String title;

	@Column(name = "_description")
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
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

		ProjectDTO other = (ProjectDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
