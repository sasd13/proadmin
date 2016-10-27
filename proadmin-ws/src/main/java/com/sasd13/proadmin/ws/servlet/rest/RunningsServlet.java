/*
 * To change this license header, choose License Headers in Running Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.Running;

/**
 *
 * @author Samir
 */
@WebServlet("/runnings")
public class RunningsServlet extends BeansServlet<Running> {

	private static final long serialVersionUID = -1274290275450535810L;

	private static final Logger LOG = Logger.getLogger(RunningsServlet.class);

	@Override
	protected Class<Running> getBeanClass() {
		return Running.class;
	}

	@Override
	protected String getWebServiceName() {
		return "RunningsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
