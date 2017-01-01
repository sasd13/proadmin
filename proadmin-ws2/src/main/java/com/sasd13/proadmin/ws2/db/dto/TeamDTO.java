package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.ITeamDAO;

@Entity
@Table(name = ITeamDAO.TABLE)
public class TeamDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1738292554927147241L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_teams_id")
	@SequenceGenerator(name = "seq_teams_id", sequenceName = "seq_teams_id")
	@Column(name = ITeamDAO.COLUMN_ID)
	private long id;

	@Column(name = ITeamDAO.COLUMN_CODE)
	private String code;

	public TeamDTO() {
	}

	public TeamDTO(Team team) {
		code = team.getNumber();
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

		TeamDTO other = (TeamDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
