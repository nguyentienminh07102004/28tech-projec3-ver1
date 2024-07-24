package com.javaweb.utils;

public class NumberUtils {
	public static boolean isNumber(String value) {
		if(value == null) return false;
		try { 
			Double.parseDouble(value);
			return true;
		}
		catch(NumberFormatException ex) {
			return false;
		}
	}
}
