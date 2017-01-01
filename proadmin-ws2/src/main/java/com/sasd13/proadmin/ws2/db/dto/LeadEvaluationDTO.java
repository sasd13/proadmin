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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;

@Entity
@Table(name = ILeadEvaluationDAO.TABLE)
public class LeadEvaluationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255571673514443352L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leadevaluations_id")
	@SequenceGenerator(name = "seq_leadevaluations_id", sequenceName = "seq_leadevaluations_id")
	@Column(name = ILeadEvaluationDAO.COLUMN_ID)
	private long id;

	@Column(name = ILeadEvaluationDAO.COLUMN_PLANNINGMARK)
	private float planningMark;

	@Column(name = ILeadEvaluationDAO.COLUMN_PLANNINGCOMMENT)
	private String planningComment;

	@Column(name = ILeadEvaluationDAO.COLUMN_COMMUNICATIONMARK)
	private float communicationMark;

	@Column(name = ILeadEvaluationDAO.COLUMN_COMMUNICATIONCOMMENT)
	private String communicationComment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ILeadEvaluationDAO.COLUMN_REPORT)
	private Report report;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ILeadEvaluationDAO.COLUMN_STUDENT)
	private Student student;

	public LeadEvaluationDTO() {
	}

	public LeadEvaluationDTO(LeadEvaluation leadevaluation) {
		planningMark = leadevaluation.getPlanningMark();
		planningComment = leadevaluation.getPlanningComment();
		communicationMark = leadevaluation.getCommunicationMark();
		communicationComment = leadevaluation.getCommunicationComment();
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
	
	public Report getReport() {
		return report;
	}
	
	public void setReport(Report report) {
		this.report = report;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((planningComment == null) ? 0 : planningComment.hashCode());
		result = prime * result + ((communicationComment == null) ? 0 : communicationComment.hashCode());
		result = prime * result + ((report == null) ? 0 : report.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());

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

		LeadEvaluationDTO other = (LeadEvaluationDTO) obj;

		if (planningComment == null && other.planningComment != null)
			return false;
		else if (!planningComment.equals(other.planningComment))
			return false;

		if (communicationComment == null && other.communicationComment != null)
			return false;
		else if (!communicationComment.equals(other.communicationComment))
			return false;

		if (report == null && other.report != null)
			return false;
		else if (!report.equals(other.report))
			return false;

		if (student == null && other.student != null)
			return false;
		else if (!student.equals(other.student))
			return false;

		return true;
	}
}
