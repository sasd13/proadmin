/*
 * To change this license header, choose License Headers in AcademicLevel Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.AcademicLevel;

/**
 *
 * @author Samir
 */
@WebServlet("/academiclevels")
public class AcademicLevelsServlet extends BeansServlet<AcademicLevel> {

	private static final long serialVersionUID = -5517780261514018166L;

	@Override
	protected Class<AcademicLevel> getBeanClass() {
		return AcademicLevel.class;
	}
}
