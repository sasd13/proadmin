package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.running.LeadEvaluation;

@Entity
@Table(name = "leadevaluations")
public class LeadEvaluationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255571673514443352L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leadevaluations_id")
	@SequenceGenerator(name = "seq_leadevaluations_id", sequenceName = "seq_leadevaluations_id")
	@Column(name = "_id")
	private long id;

	@Column(name = "_planningmark", precision = 4, scale = 2)
	private float planningMark;

	@Column(name = "_planningcomment")
	private String planningComment;

	@Column(name = "_communicationmark", precision = 4, scale = 2)
	private float communicationMark;

	@Column(name = "_communicationcomment")
	private String communicationComment;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_report")
	private ReportDTO report;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_student")
	private StudentDTO student;

	public LeadEvaluationDTO() {
	}

	public LeadEvaluationDTO(LeadEvaluation leadEvaluation) {
		planningMark = leadEvaluation.getPlanningMark();
		planningComment = leadEvaluation.getPlanningComment();
		communicationMark = leadEvaluation.getCommunicationMark();
		communicationComment = leadEvaluation.getCommunicationComment();
		report = new ReportDTO(leadEvaluation.getReport());
		student = new StudentDTO(leadEvaluation.getStudent());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPlanningMark() {
		return planningMark;
	}

	public void setPlanningMark(float planningMark) {
		this.planningMark = planningMark;
	}

	public String getPlanningComment() {
		return planningComment;
	}

	public void setPlanningComment(String planningComment) {
		this.planningComment = planningComment;
	}

	public float getCommunicationMark() {
		return communicationMark;
	}

	public void setCommunicationMark(float communicationMark) {
		this.communicationMark = communicationMark;
	}

	public String getCommunicationComment() {
		return communicationComment;
	}

	public void setCommunicationComment(String communicationComment) {
		this.communicationComment = communicationComment;
	}

	public ReportDTO getReport() {
		return report;
	}

	public void setReport(ReportDTO report) {
		this.report = report;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((report == null) ? 0 : report.hashCode());

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

		LeadEvaluationDTO other = (LeadEvaluationDTO) obj;

		if (report == null && other.report != null)
			return false;
		else if (!report.equals(other.report))
			return false;

		return true;
	}
}
