/*
 * To change this license header, choose License Headers in Teacher Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.member.Teacher;

/**
 *
 * @author Samir
 */
@WebServlet("/teachers")
public class TeachersServlet extends BeansServlet<Teacher> {

	private static final long serialVersionUID = 1390483642480552723L;

	@Override
	protected Class<Teacher> getBeanClass() {
		return Teacher.class;
	}
}
