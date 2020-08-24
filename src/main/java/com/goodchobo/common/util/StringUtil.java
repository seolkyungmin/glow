package com.goodchobo.common.util;

import java.text.DecimalFormat;
import java.util.List;

public class StringUtil {

	/**
	 * 숫자값만 가져오기
	 */
	public static String getNumber(String str) {

		if (isEmpty(str)) {
			return str;
		}
		char[] chars = str.toCharArray();
		int pos = 0;
		for (int i = 0; i < chars.length; i++) {
			if (Character.isDigit(chars[i])) {
				chars[pos++] = chars[i];
			}
		}
		return new String(chars, 0, pos);
	}

	/**
	 * 빈값인지 체크
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 유효값인지 체크
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	/**
	 * null일 경우 0 리턴
	 * @param str
	 * @return
	 */
	public static int nullToZero(String str) {
		if(isEmpty(str)) {
			str = "0";
		};
		return Integer.parseInt(str);
	}

	/**
	 * null일 경우 빈값 리턴
	 * @param str
	 * @return
	 */
	public static String nullToStr(String str) {
		return nullToStr(str, "");
	}

	/**
	 * null일 경우 replaceStr 리턴
	 * @param str
	 * @param replaceStr
	 * @return
	 */
	public static String nullToStr(String str, String replaceStr) {
		if(isEmpty(str)) {
			str = replaceStr;
		};
		return str;
	}

	public static String checkEmpty(String value){
		if(null==value || "".equals(value))
			return "";
		else
			return value;
	}

	public static String checkComma(String value){
		String temp = checkEmpty(value);
		if(temp.indexOf(",")>-1)
			return "\""+temp+"\"";
		else
			return temp;
	}

	 /**
	* 숫자에 천단위마다 콤마 넣기
	* @param int
	* @return String
	* */
	public static String toNumFormat(int num) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(num);
	}


	/**
	 * null일 경우 null 리턴 아닐 경우 String 리턴
	 * @param obj
	 * @return
	 */
	public static String nullCheckStr(Object obj) {
		return obj != null ? obj.toString() : null;
	}

	public static String nullCheckSize(List<?> list) {
		return list != null ? Integer.toString(list.size()) : null;
	}

	/**
	 * null일 경우 빈값 리턴
	 * @param obj
	 * @return
	 */
	public static String nullToStr(Object obj) {
		return nullToStr(obj, "");
	}

	/**
	 * null일 경우 replaceStr 리턴
	 * @param obj
	 * @param replaceStr
	 * @return
	 */
	public static String nullToStr(Object obj, String replaceStr) {
		return obj != null ? obj.toString() : replaceStr;
	}
	
	public static String regnumMaskin(String regnum) {
		if(regnum != null && regnum.contains("-") && regnum.length() == 14) {
			return regnum.replaceAll("(.{6}$)", "******");
		} else {
			return regnum;
		}
	}

	/**
	 * 문자열 합치기
	 * @param args
	 * @return
	 */
	public static String join(Object... args) {

		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			if (arg != null) {
				sb.append(arg);
			}
		}
		return sb.toString();
	}
}
