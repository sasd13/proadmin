package com.sasd13.proadmin.aaa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sasd13.proadmin.aaa.bean.EnumStatus;
import com.sasd13.proadmin.aaa.util.EnumStatusConverter;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623005553869096068L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_uid")
	private String userID;

	@Column(name = "_username")
	private String username;

	@Column(name = "_password")
	private String password;

	@Column(name = "_status")
	@Convert(converter = EnumStatusConverter.class)
	private EnumStatus status;

	@Column(name = "_roles")
	private String roles;

	@Column(name = "_intermediary")
	private String intermediary;

	@Column(name = "_email")
	private String email;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<UserPreference> userPreferences;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserPreference> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(List<UserPreference> userPreferences) {
		this.userPreferences = userPreferences;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((userID == null) ? 0 : userID.hashCode());

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

		User other = (User) obj;

		if (userID == null && other.userID != null)
			return false;
		else if (!userID.equals(other.userID))
			return false;

		return true;
	}
}
