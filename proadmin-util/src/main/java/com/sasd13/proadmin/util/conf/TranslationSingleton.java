package com.sasd13.proadmin.util.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class TranslationSingleton {

	private static class TranslationSingletonHolder {
		private static final TranslationSingleton INSTANCE = new TranslationSingleton();
	}

	private static final Logger LOG = Logger.getLogger(TranslationSingleton.class);
	private static final String DIRECTORY_TRANSLATION = "languages/";
	private static final String PREFIX = "Proadmin_";

	private Map<String, Properties> properties;

	private TranslationSingleton() {
		properties = new HashMap<>();

		String path = System.getProperty(DIRECTORY_TRANSLATION);
		File folder = new File(path);
		File[] files = folder.listFiles();

		String key = null;
		Properties props = null;
		FileInputStream fis = null;

		for (File file : files) {
			if (file.isFile() && file.getName().startsWith(PREFIX)) {
				key = FilenameUtils.removeExtension(file.getName().split(PREFIX)[1]);
				props = new Properties();

				try {
					fis = new FileInputStream(path + file.getName());
					props.loadFromXML(fis);
				} catch (IOException e) {
					LOG.error(e);
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							LOG.warn(e);
						}
					}
				}

				properties.put(key, props);
			}
		}
	}

	public static TranslationSingleton getInstance() {
		return TranslationSingletonHolder.INSTANCE;
	}

	public Properties getProperties(String extension) {
		return properties.get(extension);
	}
}
