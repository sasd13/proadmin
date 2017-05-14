package com.sasd13.proadmin.aaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userpreferences")
public class UserPreference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5608222361321314705L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_value")
	private String value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_user")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_preference")
	private Preference preference;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((preference == null) ? 0 : preference.hashCode());

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

		UserPreference other = (UserPreference) obj;

		if (user == null && other.user != null)
			return false;
		else if (!user.equals(other.user))
			return false;

		if (preference == null && other.preference != null)
			return false;
		else if (!preference.equals(other.preference))
			return false;

		return true;
	}
}
