package com.sasd13.proadmin.backend.controller;

import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;

public abstract class Controller {

	protected void addHeaders(SearchBean searchBean, ResponseBean responseBean, int size) {
		responseBean.getHeader().getApplicativeContext().setLanguageISOCode(responseBean.getHeader().getApplicativeContext().getLanguageISOCode());
		responseBean.getHeader().getApplicativeContext().setAdditionalProperties(searchBean.getHeader().getApplicativeContext().getAdditionalProperties());
		responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(size));
	}
}
