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

@Entity
@Table(name = "teachers")
public class TeacherDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8155302577979140320L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_teachers_id")
	@SequenceGenerator(name = "seq_teachers_id", sequenceName = "seq_teachers_id")
	@Column(name = "_id")
	private long id;

	@Column(name = "_code")
	private String number;

	@Column(name = "_firstname")
	private String firstName;

	@Column(name = "_lastname")
	private String lastName;

	@Column(name = "_email")
	private String email;

	public TeacherDTO() {
	}

	public TeacherDTO(Teacher teacher) {
		number = teacher.getNumber();
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

		result = prime * result + ((number == null) ? 0 : number.hashCode());

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

		TeacherDTO other = (TeacherDTO) obj;

		if (number == null && other.number != null)
			return false;
		else if (!number.equals(other.number))
			return false;

		return true;
	}
}
