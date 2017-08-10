package com.foreign.webmagic;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.foreign.dao.ForeigninfoDao;
import com.foreign.dao.PagesDao;
import com.foreign.pojo.Foreigninfo;
import com.foreign.pojo.Pages;
import com.foreign.util.HtmlUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WebMagicPageTest implements PageProcessor {
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setCharset("utf-8").setTimeOut(10*1000).setCycleRetryTimes(3).setSleepTime(1 * 500)
			.addHeader("Connection", "keep-alive")
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");

	public Pages pages;

	public PagesDao pageDao;
	
	public ForeigninfoDao infoDao;

	public WebMagicPageTest(Pages pages, PagesDao pageDao, ForeigninfoDao infoDao) {
		this.pages = pages;
		this.pageDao = pageDao;
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

			List<String> urlList = HtmlUtil.getUrlList(document);

			List<Foreigninfo> entitys = new ArrayList<Foreigninfo>();
			for (String url : urlList) {
				Foreigninfo entity = new Foreigninfo();
				entity.setUrls(url);
				entity.setIsquerys(0);
				entitys.add(entity);
			}
			infoDao.save(entitys);
			pages.setState(1);
		} catch (Exception e) {
			pages.setState(-1);
		} finally {
			pageDao.save(pages);
		}
	}

}
