package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.ws2.db.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;

@Entity
@Table(name = IReportDAO.TABLE)
public class ReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2380228683911940802L;

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
	@JoinColumn(name = IReportDAO.COLUMN_RUNNINGTEAM)
	private RunningTeamDTO runningTeam;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = ILeadEvaluationDAO.COLUMN_REPORT)
	private LeadEvaluationDTO leadEvaluation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = IIndividualEvaluationDAO.COLUMN_REPORT)
	private List<IndividualEvaluationDTO> individualEvaluations;

	public ReportDTO() {
	}

	public ReportDTO(Report report) {
		code = report.getNumber();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getDateMeeting() {
		return dateMeeting;
	}

	public void setDateMeeting(Timestamp dateMeeting) {
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
	
	public LeadEvaluationDTO getLeadEvaluation() {
		return leadEvaluation;
	}
	
	public void setLeadEvaluation(LeadEvaluationDTO leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}
	
	public List<IndividualEvaluationDTO> getIndividualEvaluations() {
		return individualEvaluations;
	}
	
	public void setIndividualEvaluations(List<IndividualEvaluationDTO> individualEvaluations) {
		this.individualEvaluations = individualEvaluations;
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

		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateMeeting == null) ? 0 : dateMeeting.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((runningTeam == null) ? 0 : runningTeam.hashCode());

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

		if (dateMeeting == null && other.dateMeeting != null)
			return false;
		else if (!dateMeeting.equals(other.dateMeeting))
			return false;

		if (comment == null && other.comment != null)
			return false;
		else if (!comment.equals(other.comment))
			return false;

		if (runningTeam == null && other.runningTeam != null)
			return false;
		else if (!runningTeam.equals(other.runningTeam))
			return false;

		return true;
	}
}
