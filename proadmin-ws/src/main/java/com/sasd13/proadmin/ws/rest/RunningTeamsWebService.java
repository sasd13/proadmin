/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
@WebServlet("/runningteams")
public class RunningTeamsWebService extends AbstractWebService<RunningTeam> {

	private static final long serialVersionUID = -6765834594099327539L;

	@Override
	protected Class<RunningTeam> getEntityClass() {
		return RunningTeam.class;
	}
}
