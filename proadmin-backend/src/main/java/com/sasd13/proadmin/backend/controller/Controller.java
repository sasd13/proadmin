package com.sasd13.proadmin.backend.controller;

import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;

public abstract class Controller {

	protected void addHeaders(SearchBean searchBean, ResponseBean responseBean) {
		responseBean.getContext().setLanguageISOCode(responseBean.getContext().getLanguageISOCode());
		responseBean.getContext().setAdditionalProperties(searchBean.getContext().getAdditionalProperties());
	}
}
