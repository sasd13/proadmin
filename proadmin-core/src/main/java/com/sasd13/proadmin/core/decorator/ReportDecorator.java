package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.Report;

public class ReportDecorator extends Report {
	
	private Report report;
	private boolean deleted;
	
	public ReportDecorator(Report report) {
		this.report = report;
	}
	
	public Report getReport() {
		return report;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
