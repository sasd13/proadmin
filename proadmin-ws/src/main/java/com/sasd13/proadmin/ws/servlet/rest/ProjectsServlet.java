/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.project.Project;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectsServlet extends BeansServlet<Project> {

	private static final long serialVersionUID = 1622591818424740680L;

	@Override
	protected Class<Project> getBeanClass() {
		return Project.class;
	}
}
