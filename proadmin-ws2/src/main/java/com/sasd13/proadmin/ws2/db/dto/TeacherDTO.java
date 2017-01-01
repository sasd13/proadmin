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
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;

@Entity
@Table(name = ITeacherDAO.TABLE)
public class TeacherDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8155302577979140320L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_teachers_id")
	@SequenceGenerator(name = "seq_teachers_id", sequenceName = "seq_teachers_id")
	@Column(name = ITeacherDAO.COLUMN_ID)
	private long id;

	@Column(name = ITeacherDAO.COLUMN_CODE)
	private String code;

	@Column(name = ITeacherDAO.COLUMN_FIRSTNAME)
	private String firstName;

	@Column(name = ITeacherDAO.COLUMN_LASTNAME)
	private String lastName;

	@Column(name = ITeacherDAO.COLUMN_EMAIL)
	private String email;

	public TeacherDTO() {
	}

	public TeacherDTO(Teacher teacher) {
		code = teacher.getNumber();
		firstName = teacher.getFirstName();
		lastName = teacher.getLastName();
		email = teacher.getEmail();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());

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

		TeacherDTO other = (TeacherDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		if (firstName == null && other.firstName != null)
			return false;
		else if (!firstName.equals(other.firstName))
			return false;

		if (lastName == null && other.lastName != null)
			return false;
		else if (!lastName.equals(other.lastName))
			return false;

		if (email == null && other.email != null)
			return false;
		else if (!email.equals(other.email))
			return false;

		return true;
	}
}
