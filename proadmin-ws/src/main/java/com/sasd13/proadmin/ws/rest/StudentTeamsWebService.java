/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.member.StudentTeam;

/**
 *
 * @author Samir
 */
@WebServlet("/studentteams")
public class StudentTeamsWebService extends AbstractWebService<StudentTeam> {

	private static final long serialVersionUID = -1702761496823324620L;

	@Override
	protected Class<StudentTeam> getEntityClass() {
		return StudentTeam.class;
	}
}
