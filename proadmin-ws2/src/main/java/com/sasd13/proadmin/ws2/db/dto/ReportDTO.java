package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sasd13.proadmin.bean.running.Report;

@Entity
@Table(name = "reports")
public class ReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2380228683911940802L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reports_id")
	@SequenceGenerator(name = "seq_reports_id", sequenceName = "seq_reports_id")
	@Column(name = "_id")
	private long id;

	@Column(name = "_code")
	private String number;

	@Column(name = "_datemeeting")
	@Temporal(TemporalType.DATE)
	private Date dateMeeting;

	@Column(name = "_session")
	private int session;

	@Column(name = "_comment")
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_runningteam")
	private RunningTeamDTO runningTeam;

	public ReportDTO() {
	}

	public ReportDTO(Report report) {
		number = report.getNumber();
		dateMeeting = new Timestamp(report.getDateMeeting().getTime());
		session = report.getSession();
		comment = report.getComment();
		runningTeam = new RunningTeamDTO(report.getRunningTeam());
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

	public Date getDateMeeting() {
		return dateMeeting;
	}

	public void setDateMeeting(Date dateMeeting) {
		this.dateMeeting = dateMeeting;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RunningTeamDTO getRunningTeam() {
		return runningTeam;
	}

	public void setRunningTeam(RunningTeamDTO runningTeam) {
		this.runningTeam = runningTeam;
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

		ReportDTO other = (ReportDTO) obj;

		if (number == null && other.number != null)
			return false;
		else if (!number.equals(other.number))
			return false;

		return true;
	}
}
