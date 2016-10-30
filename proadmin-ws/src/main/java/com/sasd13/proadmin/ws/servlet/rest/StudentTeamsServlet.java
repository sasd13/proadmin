/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.bean.member.StudentTeam;

/**
 *
 * @author Samir
 */
@WebServlet("/studentteams")
public class StudentTeamsServlet extends BeansServlet<StudentTeam> {

	private static final long serialVersionUID = 9150723342716530893L;

	private static final Logger LOG = Logger.getLogger(StudentTeamsServlet.class);

	@Override
	protected Class<StudentTeam> getBeanClass() {
		return StudentTeam.class;
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}
