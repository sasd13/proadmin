/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sasd13.proadmin.aaa.util.Names;

/**
 *
 * @author Samir
 */
public class Config {

	private static class ConfigHolder {
		private static final Config INSTANCE = new Config();
	}

	private static final Logger LOG = Logger.getLogger(Config.class);
	private static final String FILE_PROPERTIES_LOG4J = "log4j.properties";

	private Config() {
	}

	public static Config getInstance() {
		return ConfigHolder.INSTANCE;
	}

	public void init() {
		initLogger();
		AppProperties.init();
		initDBDriver();
	}

	private void initLogger() {
		Properties properties = new Properties();
		InputStream in = null;

		try {
			in = Config.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES_LOG4J);

			if (in != null) {
				properties.load(in);
				PropertyConfigurator.configure(properties);
			} else {
				BasicConfigurator.configure();
				Logger.getLogger(Config.class).error("LOG4J configuration file not found: " + System.getProperty(FILE_PROPERTIES_LOG4J));
			}
		} catch (IOException e) {
			if (LOG != null) {
				LOG.fatal(e);
			} else {
				BasicConfigurator.configure();
				Logger.getLogger(Config.class).fatal(e);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.warn(e);
				}
			}
		}
	}

	private void initDBDriver() {
		try {
			Class.forName(AppProperties.getProperty(Names.AAA_DB_DRIVER));
		} catch (ClassNotFoundException e) {
			LOG.fatal(e);
		}
	}
}
