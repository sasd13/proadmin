package com.sasd13.proadmin.ws2.dao.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.level.AcademicLevel;

@Entity
@Table(name = "academiclevels")
public class AcademicLevelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8100226380687413119L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_code")
	private String code;

	public AcademicLevelDTO() {
	}

	public AcademicLevelDTO(AcademicLevel academicLevel) {
		code = academicLevel.getCode();
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

		AcademicLevelDTO other = (AcademicLevelDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
