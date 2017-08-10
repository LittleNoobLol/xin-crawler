package com.foreign.util;

public class StringUtil {

	/**
	 * 获取url的分类
	 * 
	 * @param url
	 * @return
	 */
	public static Integer getTypes(String url) throws Exception{
		return Integer.valueOf(url.substring(0,url.indexOf('/')));
	}
	
	public static String getInnerText(String text,int length){
		text = text.substring(length,text.length());
		return text;
	}

}
