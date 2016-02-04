package com.joker.core.mvc.config;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joker.core.mvc.utils.StringUtils;

public class JokerConfig {
	protected final Log logger = LogFactory.getLog(getClass());

	private String configFile;

	private String encode = "UTF-8";

	private Map<String, String> urlMapping = new Hashtable<String, String>();

	public JokerConfig(String configFile) {
		this.configFile = configFile;
	}

	public void parse() {

		if (StringUtils.isNull(configFile)) {

			if (logger.isInfoEnabled()) {
				logger
						.info("Parse XML JokerConfig failed! The config file is null");
			}

		} else {

			if (logger.isInfoEnabled()) {
				logger.info("Parse XML JokerConfig [" + configFile
						+ "] started!");
			}
			long startTime = System.currentTimeMillis();
			XmlParseUtils.parse(this, configFile);
			if (logger.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				logger.info("Parse  JokerConfig  completed   in   "
						+ elapsedTime + " ms!");
			}
		}
	}

	public void setUrlMapping(String url, String classPath) {
		if (!StringUtils.isNull(url) && !StringUtils.isNull(classPath)) {

			urlMapping.put(url, classPath);
		}
	}

	public String getClassPath(String url) {
		if (!StringUtils.isNull(url)) {

			return this.urlMapping.get(url);
		}
		return null;
	}

	public Iterator<String> iteratoKey() {
		return this.urlMapping.keySet().iterator();
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

}
