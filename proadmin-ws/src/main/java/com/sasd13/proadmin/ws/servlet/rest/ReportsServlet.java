/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.Report;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsServlet extends BeansServlet<Report> {

	private static final long serialVersionUID = -574346007593042394L;

	private static final Logger LOG = Logger.getLogger(ReportsServlet.class);

	@Override
	protected Class<Report> getBeanClass() {
		return Report.class;
	}

	@Override
	protected String getWebServiceName() {
		return "ReportsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
