package com.defonds.util.tool;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReadProperties {
	private static final Log logger = LogFactory.getLog(ReadProperties.class);
	private static final Properties properties = new Properties();
	static {
		java.io.InputStream in;
		try {
			in = ReadProperties.class.getResourceAsStream("/swiftonrsa.properties");
			properties.load(in);
		} catch (Exception e) {
			logger.error("java.util.Properties.ReadProperties.initFile error:", e);
		}
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}
