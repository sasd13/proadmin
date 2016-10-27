/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sasd13.proadmin.aaa.Config;

/**
 *
 * @author Samir
 */
public class InitializerServlet extends HttpServlet {

	private static final long serialVersionUID = -7528145890196166782L;

	@Override
	public void init() throws ServletException {
		super.init();

		Config.getInstance().init();
	}
}
