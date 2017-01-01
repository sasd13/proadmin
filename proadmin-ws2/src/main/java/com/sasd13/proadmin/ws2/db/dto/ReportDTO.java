package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;

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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.IReportDAO;

@Entity
@Table(name = IReportDAO.TABLE)
public class ReportDTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reports_id")
	@SequenceGenerator(name = "seq_reports_id", sequenceName = "seq_reports_id")
	@Column(name = IReportDAO.COLUMN_ID)
	private long id;

	@Column(name = IReportDAO.COLUMN_CODE)
	private String code;

	@Column(name = IReportDAO.COLUMN_DATEMEETING)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateMeeting;

	@Column(name = IReportDAO.COLUMN_SESSION)
	private int session;

	@Column(name = IReportDAO.COLUMN_COMMENT)
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "")
	private RunningTeam runningTeam;

	public ReportDTO() {
	}

	public ReportDTO(Report report) {
		code = report.getNumber();
		dateMeeting = new Timestamp(report.getDateMeeting().getTime());
		session = report.getSession();
		comment = report.getComment();
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

		ReportDTO other = (ReportDTO) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
