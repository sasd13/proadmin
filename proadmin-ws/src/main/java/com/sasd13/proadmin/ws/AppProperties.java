package com.sasd13.proadmin.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppProperties {

	private static final Logger LOG = Logger.getLogger(AppProperties.class);
	private static final String FILE_PROPERTIES_INFRA = "infra.properties";
	private static final String FILE_PROPERTIES_OPE = "ope.properties";

	private static Properties properties = new Properties();

	private AppProperties() {
	}

	public static synchronized void init() {
		loadProperties(FILE_PROPERTIES_INFRA);
		loadProperties(FILE_PROPERTIES_OPE);
	}

	private static void loadProperties(String filePath) {
		InputStream in = null;

		try {
			in = Config.class.getClassLoader().getResourceAsStream(filePath);

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
