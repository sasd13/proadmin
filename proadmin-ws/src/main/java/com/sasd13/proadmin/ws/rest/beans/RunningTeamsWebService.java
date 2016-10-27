/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest.beans;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
@WebServlet("/runningteams")
public class RunningTeamsWebService extends BeansWebService<RunningTeam> {

	private static final long serialVersionUID = -8420663078069520851L;

	private static final Logger LOG = Logger.getLogger(RunningTeamsWebService.class);

	@Override
	protected Class<RunningTeam> getBeanClass() {
		return RunningTeam.class;
	}

	@Override
	protected String getWebServiceName() {
		return "RunningTeamsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
