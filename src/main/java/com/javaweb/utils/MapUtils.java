package com.javaweb.utils;

import java.util.Map;

public class MapUtils {
	public static <T> T getObject(Map<String, String> maps, String key, Class<T> tClass) {
		String str = maps.getOrDefault(key, null);
		Object result = null;
		if(str != null) {
			if(tClass.getTypeName().equals("java.lang.Long")) {
				result = str != "" ? Long.valueOf(str) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.Integer")) {
				result  = str != "" ? Integer.valueOf(str.toString()) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.String")) {
				result = str.toString();
			}
			return tClass.cast(result);
		}
		return null;
	}

//	public static <T> T getObject(Object item, Class<T> tClass) {
//		if(item != null) {
//			if(tClass.getTypeName().equals("java.lang.Long")) {
//				item = item != "" ? Long.valueOf(item.toString()) : null;
//			}
//			else if(tClass.getTypeName().equals("java.lang.Integer")) {
//				item = item != "" ? Integer.valueOf(item.toString()) : null;
//			}
//			else if(tClass.getTypeName().equals("java.lang.String")) {
//				item = item.toString();
//			}
//			return tClass.cast(item);
//		}
//		return null;
//	}
}
