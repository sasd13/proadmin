package com.sasd13.proadmin.backend.entity;

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
@Table(name = "individualevaluations")
public class IndividualEvaluation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5531055463325939872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_mark", precision = 4, scale = 2)
	private float mark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_report")
	private Report report;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_student")
	private Student student;

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

		IndividualEvaluation other = (IndividualEvaluation) obj;

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
