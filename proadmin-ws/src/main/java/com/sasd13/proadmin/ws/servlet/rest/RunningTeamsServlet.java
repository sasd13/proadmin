/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
@WebServlet("/runningteams")
public class RunningTeamsServlet extends BeansServlet<RunningTeam> {

	private static final long serialVersionUID = -8420663078069520851L;

	private static final Logger LOG = Logger.getLogger(RunningTeamsServlet.class);

	@Override
	protected Class<RunningTeam> getBeanClass() {
		return RunningTeam.class;
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
