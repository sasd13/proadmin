/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.project.Project;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectsWebService extends AbstractWebService<Project> {

	private static final long serialVersionUID = -3267052705268881300L;

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}
}
