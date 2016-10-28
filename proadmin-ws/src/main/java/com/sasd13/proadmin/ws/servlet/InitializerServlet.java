/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sasd13.javaex.util.conf.AppProperties;
import com.sasd13.javaex.util.conf.Config;
import com.sasd13.proadmin.util.Names;

/**
 *
 * @author Samir
 */
public class InitializerServlet extends HttpServlet {

	private static final long serialVersionUID = 456590755035038993L;

	private static final String LOG4J_PROPERTIES = "log4j.properties";
	private static final String INFRA_PROPERTIES = "infra.properties";
	private static final String OPE_PROPERTIES = "ope.properties";

	@Override
	public void init() throws ServletException {
		super.init();

		Config config = Config.getInstance();

		config.initLogger(LOG4J_PROPERTIES);
		config.initAppProperties(getClass().getClassLoader(), new String[] { INFRA_PROPERTIES, OPE_PROPERTIES });
		config.initDBDriver(AppProperties.getProperty(Names.WS_DB_DRIVER));
	}
}
