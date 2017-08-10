package com.foreign.constant;

import java.util.HashMap;
import java.util.Map;

public class HeaderConstant {

	private static Map<String, String> header;

	static {
		header = new HashMap<String, String>();
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		header.put("Accept-Encoding", "gzip, deflate, sdch");
		header.put("Accept-Language", "zh-CN,zh;q=0.8");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "keep-alive");
		header.put("Upgrade-Insecure-Requests", "1");
		header.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	}

	public static void initCookie(String cookies) {
		header.put("Cookie", cookies);
	}

	public static Map<String, String> getHeaderPage() {
		return header;
	}

}
