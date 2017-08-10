package com.foreign.webmagic;

import org.jsoup.nodes.Document;

import com.foreign.dao.ForeigninfoDao;
import com.foreign.pojo.Foreigninfo;
import com.foreign.util.HtmlUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WebMagicTest implements PageProcessor {
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setCharset("utf-8").setCycleRetryTimes(3).setSleepTime(1 * 500)
			.addHeader("Connection", "keep-alive")
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");

	public Foreigninfo info;

	public ForeigninfoDao infoDao;

	public WebMagicTest(Foreigninfo info, ForeigninfoDao infoDao) {
		this.info = info;
		this.infoDao = infoDao;
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		try {
			Document document = page.getHtml().getDocument();
			HtmlUtil.infoAnalysis(info.getUrls(), document, info);
			info.setIsquerys(1);
		} catch (Exception e) {
			info.setIsquerys(-1);
		} finally {
			infoDao.save(info);
		}
	}

}
