package com.sasd13.proadmin.util.conf;

import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TranslationBundle {
	
	private static final Logger LOG = Logger.getLogger(TranslationBundle.class);
	private static final String DEFAULT_LANGUAGE = "en";

	private Properties properties, defaultProperties;
	private String language;
	
	public TranslationBundle(Locale locale) {
		TranslationSingleton ts = TranslationSingleton.getInstance();
		defaultProperties = ts.getProperties(DEFAULT_LANGUAGE);
		language = locale.toString();
		properties = ts.getProperties(language);
		
		if (properties == null) {
			LOG.error("Translation Bundle not found for the language: " + language);
		}
	}
	
	public String getString(String key) {
		String value = null;
		
		if (properties != null) {
			value = properties.getProperty(key);
		} else {
			LOG.error("Translation Bundle not found for the language: " + language);
		}
		
		if (value == null) {
			LOG.error("key '" + key + "' is unknown in the language file, language=" + language);
			
			if (LOG.isDebugEnabled()) {
				value = "??" + key + "??";
			} else {
				value = "???";
			}
		} else if (value.isEmpty()) {
			if (defaultProperties != null) {
				value = defaultProperties.getProperty(key);
				
				if (value == null) {
					LOG.error("key '" + key + "' is unknown in the language file, language=" + DEFAULT_LANGUAGE);
					
					if (LOG.isDebugEnabled()) {
						value = "??" + key + "??";
					} else {
						value = "???";
					}
				}
			} else {
				LOG.fatal("Translation Bundle not found for the language: " + DEFAULT_LANGUAGE);
			}
		}
		
		return value;
	}
}
