package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

public class ReportUpdateWrapperValidator implements IValidator<IUpdateWrapper<Report>> {

	private ReportValidator reportValidator;

	public ReportUpdateWrapperValidator() {
		reportValidator = new ReportValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Report> updateWrapper) throws ValidatorException {
		IReportUpdateWrapper reportUpdateWrapper = (IReportUpdateWrapper) updateWrapper;

		if (reportUpdateWrapper == null) {
			throw new ValidatorException("ReportUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(reportUpdateWrapper.getNumber())) {
			throw new ValidatorException("ReportUpdateWrapper : number is not valid");
		}

		reportValidator.validate(reportUpdateWrapper.getWrapped());
	}
}
