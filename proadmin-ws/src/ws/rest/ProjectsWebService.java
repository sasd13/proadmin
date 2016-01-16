/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.proadmin.core.bean.project.Project;

import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectsWebService extends AbstractWebService<Project> {
	
	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}
}
