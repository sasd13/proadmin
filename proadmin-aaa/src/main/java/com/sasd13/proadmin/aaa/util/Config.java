/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 *
 * @author Samir
 */
public class Config {

	private static final Logger LOG = Logger.getLogger(Config.class);
	private static final String FILE = "config.properties";

	private static Properties properties;

	static {
		properties = new Properties();
		InputStream in = null;

		try {
			in = Config.class.getClassLoader().getResourceAsStream(FILE);

			properties.load(in);
		} catch (IOException e) {
			LOG.fatal(e);
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

	public static String getInfo(String key) {
		return properties.getProperty(key);
	}
}
