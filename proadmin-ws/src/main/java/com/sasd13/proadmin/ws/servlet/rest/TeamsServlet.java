/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.member.Team;

/**
 *
 * @author Samir
 */
@WebServlet("/teams")
public class TeamsServlet extends BeansServlet<Team> {

	private static final long serialVersionUID = -416118742023104197L;

	private static final Logger LOG = Logger.getLogger(TeamsServlet.class);

	@Override
	protected Class<Team> getBeanClass() {
		return Team.class;
	}

	@Override
	protected String getWebServiceName() {
		return "TeamsWebService";
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
