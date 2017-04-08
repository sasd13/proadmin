package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.Student;

public abstract class Evaluation {

	private Report report;
	private Student student;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Evaluation other = (Evaluation) obj;

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
