/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Samir
 */
public class Config {
	
	private static final String FILE = "config.properties";
	
	public static final String DB_DRIVER = "db_driver";
	public static final String DB_URL = "db_url";
	public static final String DB_USERNAME = "db_username";
	public static final String DB_PASSWORD = "db_password";
	
	private static Properties properties;
	
	static {
		properties = new Properties();
		InputStream in = null;
		
		try {
			in = Config.class.getClassLoader().getResourceAsStream(FILE);
			
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getInfo(String key) {
		return properties.getProperty(key);
	}
}