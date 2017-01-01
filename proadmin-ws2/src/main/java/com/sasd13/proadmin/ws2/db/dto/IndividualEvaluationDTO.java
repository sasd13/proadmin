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
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IIndividualEvaluationDAO;

@Entity
@Table(name = IIndividualEvaluationDAO.TABLE)
public class IndividualEvaluationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7862225491342411399L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_individualevaluations_id")
	@SequenceGenerator(name = "seq_individualevaluations_id", sequenceName = "seq_individualevaluations_id")
	@Column(name = IIndividualEvaluationDAO.COLUMN_ID)
	private long id;

	@Column(name = IIndividualEvaluationDAO.COLUMN_MARK)
	private float mark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = IIndividualEvaluationDAO.COLUMN_REPORT_CODE)
	private Report report;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = IIndividualEvaluationDAO.COLUMN_STUDENT_CODE)
	private Student student;

	public IndividualEvaluationDTO() {
	}

	public IndividualEvaluationDTO(IndividualEvaluation individualEvaluation) {
		mark = individualEvaluation.getMark();
		report = individualEvaluation.getReport();
		student = individualEvaluation.getStudent();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
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

		IndividualEvaluationDTO other = (IndividualEvaluationDTO) obj;

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
