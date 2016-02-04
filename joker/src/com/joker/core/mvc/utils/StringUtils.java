package com.joker.core.mvc.utils;

public class StringUtils {

	public static boolean isNull(String s) {
		if(null == s || ("".equals(s.trim()))){return true;}
		return false;
	}
	
	public static boolean isFullNull(String s){
		if(null==s||("".equals(s.trim()))||("null".equalsIgnoreCase(s.trim()))){return true;}
		return false;
		
	} 

}
