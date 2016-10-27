/*
 * To change this license header, choose License Headers in Teacher Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest.beans;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.member.Teacher;

/**
 *
 * @author Samir
 */
@WebServlet("/teachers")
public class TeachersWebService extends BeansWebService<Teacher> {

	private static final long serialVersionUID = 1390483642480552723L;

	private static final Logger LOG = Logger.getLogger(TeachersWebService.class);

	@Override
	protected Class<Teacher> getBeanClass() {
		return Teacher.class;
	}

	@Override
	protected String getWebServiceName() {
		return "TeachersWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
