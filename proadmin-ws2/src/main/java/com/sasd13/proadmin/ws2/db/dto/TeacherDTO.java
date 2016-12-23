package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.javaex.orm.IDTO;
import com.sasd13.proadmin.bean.member.Teacher;

@Entity
@Table(name = "teachers")
public class TeacherDTO implements Serializable, IDTO<Teacher> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8155302577979140320L;
	
	public static final String TABLE = "teachers";
	public static final String COLUMN_CODE = "_code";
	public static final String COLUMN_FIRSTNAME = "_firstname";
	public static final String COLUMN_LASTNAME = "_lastname";
	public static final String COLUMN_EMAIL = "_email";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_teachers_id")
	@SequenceGenerator(name = "seq_teachers_id", sequenceName = "SEQ_TEACHERS_ID")
	@Column(name = "_id")
	private long id;
	
	@Column(name = COLUMN_CODE)
	private String code;
	
	@Column(name = COLUMN_FIRSTNAME)
	private String firstName;
	
	@Column(name = COLUMN_LASTNAME)
	private String lastName;
	
	@Column(name = COLUMN_EMAIL)
	private String email;
	
	public TeacherDTO() {
	}
	
	public TeacherDTO(Teacher teacher) {
		code = teacher.getNumber();
		firstName = teacher.getFirstName();
		lastName = teacher.getLastName();
		email = teacher.getEmail();
	}
	
	@Override
	public Teacher toBean() {
		Teacher teacher = new Teacher();
		
		teacher.setNumber(code);
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setEmail(email);
		
		return teacher;
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
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		TeacherDTO other = (TeacherDTO) obj;
		
		if (code == null && other.code != null) return false;
		else if (!code.equals(other.code)) return false;
		
		if (firstName == null && other.firstName != null) return false;
		else if (!firstName.equals(other.firstName)) return false;
		
		if (lastName == null && other.lastName != null) return false;
		else if (!lastName.equals(other.lastName)) return false;
		
		if (email == null && other.email != null) return false;
		else if (!email.equals(other.email)) return false;
		
		return true;
	}
}
