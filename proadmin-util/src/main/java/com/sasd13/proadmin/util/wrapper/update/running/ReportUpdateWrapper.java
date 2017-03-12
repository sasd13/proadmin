package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Report;

public class ReportUpdateWrapper implements IUpdateWrapper<Report> {

	private Report report;
	private String number;

	@Override
	public Report getWrapped() {
		return report;
	}

	@Override
	public void setWrapped(Report report) {
		this.report = report;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
