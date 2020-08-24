package com.goodchobo.common.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * 프로퍼티 유틸
 *
 */
public class PropertiesUtil {

	private PropertiesUtil() {
	}

	private static Properties properties = null;

	/**
	 * 프로퍼티 파일로 부터 프로퍼티를 로드 한다
	 */
	private static void loadProperties() {

		FileInputStream fis = null;
		try {
			properties = new Properties();
			fis = new FileInputStream(PropertiesUtil.class.getResource("/properties/config.properties").getPath().toString());
			properties.load(new java.io.BufferedInputStream(fis));
		} catch (Exception e) {
			properties = null;
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 */
	public static String getProperty(String keyName) {

		if (properties == null) {
			loadProperties();
		}
		String value = properties.getProperty(keyName);
		return value == null ? null : value.trim();
	}

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 */
	public static String[] getAsStringArray(String keyName) {

		if (properties == null) {
			loadProperties();
		}
		StringTokenizer st = new StringTokenizer(properties.getProperty(keyName), ",");
		String[] array = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			array[i++] = st.nextToken().trim();
		}
		return array;
	}

}
