package com.sasd13.proadmin.aaa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppProperties {

	private static final Logger LOG = Logger.getLogger(AppProperties.class);
	private static final String FILE_PROPERTIES_INFRA = "infra.properties";

	private static Properties properties;

	private AppProperties() {
	}

	public static synchronized void init() {
		loadPropertiesInfra();
	}

	private static void loadPropertiesInfra() {
		properties = new Properties();
		InputStream in = null;

		try {
			in = Config.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES_INFRA);

			if (in != null) {
				properties.load(in);
			}
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

	public static String getProperty(String key) {
		String value = properties.getProperty(key);

		if (value == null) {
			LOG.error("property key '" + key + "' is unknown");
		}

		return value;
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}
