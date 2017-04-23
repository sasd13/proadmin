/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.conf.Config;
import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.proadmin.aaa.util.Names;

/**
 *
 * @author Samir
 */
public class Configuration extends HttpServlet {

	private static final long serialVersionUID = -7528145890196166782L;

	private static final String LOG4J_PROPERTIES = "log4j.properties";
	private static final String INFRA_PROPERTIES = "infra.properties";
	private static final String OPE_PROPERTIES = "ope.properties";

	@Override
	public void init() throws ServletException {
		super.init();

		initConfig();
		initTranslationBundle();
	}

	private void initConfig() {
		Config.initLogger(LOG4J_PROPERTIES);
		Config.initAppProperties(new String[] { INFRA_PROPERTIES, OPE_PROPERTIES });
		Config.initDBDriver(AppProperties.getProperty(Names.AAA_DB_DRIVER));
	}

	private void initTranslationBundle() {
		TranslationBundle.init(AppProperties.getProperty(Names.AAA_TRANSLATION_DIRECTORY_PATH), AppProperties.getProperty(Names.AAA_TRANSLATION_FILE_PREFIX), AppProperties.getProperty(Names.AAA_TRANSLATION_LANGUAGE_DEFAULT));
	}
}
