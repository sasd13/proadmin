package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;

@Entity
@Table(name = IAcademicLevelDAO.TABLE)
public class RunningDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8100226380687413119L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_academiclevels_id")
	@SequenceGenerator(name = "seq_academiclevels_id", sequenceName = "seq_academiclevels_id")
	@Column(name = IAcademicLevelDAO.COLUMN_ID)
	private long id;

	@Column(name = IAcademicLevelDAO.COLUMN_CODE)
	private String code;

	public RunningDTO() {
	}

	public RunningDTO(AcademicLevel academiclevel) {
		code = academiclevel.getCode();
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

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
