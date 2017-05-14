package com.sasd13.proadmin.aaa.controller;

import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;

public abstract class Controller {

	protected void addHeaders(ResponseBean responseBean, int size) {
		responseBean.getHeader().getApplicativeContext().setLanguageISOCode(responseBean.getHeader().getApplicativeContext().getLanguageISOCode());
		responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(size));
	}

	protected void addHeaders(ResponseBean responseBean, int size, SearchBean searchBean) {
		addHeaders(responseBean, size);
		responseBean.getHeader().getApplicativeContext().setAdditionalProperties(searchBean.getHeader().getApplicativeContext().getAdditionalProperties());
	}
}
