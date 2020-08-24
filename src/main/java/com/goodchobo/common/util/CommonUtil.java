package com.goodchobo.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommonUtil {
	/**
	 * 	URL Encode
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str) {
		return str.replaceAll("\\+", "%2B").replaceAll("&", "%26");
	}

	public static <T> boolean isEmptyList(List<T> list) {
		return (getListSize(list) == 0 ? true : false);
	}

	public static <T> boolean isEmptyList(ArrayList<T> arrayList) {
		return (getListSize(arrayList) == 0 ? true : false);
	}

	public static <T> boolean isNotEmptyList(List<T> list) {
		return !isEmptyList(list);
	}

	public static <T> boolean isNotEmptyList(ArrayList<T> arrayList) {
		return !isEmptyList(arrayList);
	}

	public static <T> int getListSize(List<T> list) {
		return (list == null ? 0 : list.size());
	}

	public static <T> int getListSize(ArrayList<T> arrayList) {
		return (arrayList == null ? 0 : arrayList.size());
	}

	public static <T> ArrayList<T> null2EmptyList(ArrayList<T> arrayList) {
		return (arrayList == null ? new ArrayList<T>() : arrayList);
	}

	public static <T> List<T> null2EmptyList(List<T> list) {
		return (list == null ? new ArrayList<T>() : list);
	}

	public static int obj2Int(Object obj) {
		return Integer.parseInt(obj.toString());
	}

	// ProductVO::getId 이렇게 사용함
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> attributeKey) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(attributeKey.apply(t));
	}
}