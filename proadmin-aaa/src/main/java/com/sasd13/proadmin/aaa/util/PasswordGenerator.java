package com.sasd13.proadmin.aaa.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.sasd13.javaex.conf.AppProperties;

public class PasswordGenerator {

	private static final int MIN_LENGTH = Integer.valueOf(AppProperties.getProperty(Names.AAA_PASSWORD_MIN_LENGTH), 6);

	public static String random() {
		return RandomStringUtils.random(MIN_LENGTH);
	}
}
