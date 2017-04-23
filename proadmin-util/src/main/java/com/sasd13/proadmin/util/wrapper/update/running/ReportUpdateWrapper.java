package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportUpdateWrapper implements IUpdateWrapper<IReport> {

	private IReport iReport;
	private String number;

	@Override
	public IReport getWrapped() {
		return iReport;
	}

	@Override
	public void setWrapped(IReport iReport) {
		this.iReport = iReport;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
