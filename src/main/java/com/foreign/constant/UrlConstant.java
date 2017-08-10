package com.foreign.constant;

public class UrlConstant {

	/**
	 * 网站根目录
	 */
	public static final String url = "http://www.zbgb.org/";

	/**
	 * 网页连接url,被弃用,由于访问页数太大了,会响应失败
	 * 
	 * @param page
	 * @return
	 */
	public static String getUrlStartOld(String page) {
		String url = "http://www.zbgb.org/StandardPList44QPage" + page + ".htm";
		return url;
	}
	
	/**
	 * 网页连接url
	 * 
	 * @param page
	 * @return
	 */
	public static String getUrlStart(String page){
		String url = "http://www.zbgb.org/Search__Type44Page"+page+".htm";
		return url;
	}
}
