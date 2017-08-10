package com.foreign;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.foreign.constant.HeaderConstant;
import com.foreign.constant.UrlConstant;
import com.foreign.dao.ForeigninfoDao;
import com.foreign.dao.PagesDao;
import com.foreign.pojo.Foreigninfo;
import com.foreign.pojo.Pages;
import com.foreign.util.FileUtil;
import com.foreign.util.HtmlUtil;
import com.foreign.util.HttpClientUtil;
import com.foreign.util.StringUtil;
import com.foreign.webmagic.WebMagicPageTest;
import com.foreign.webmagic.WebMagicTest;

import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Resource
	PagesDao pageDao;

	@Resource
	ForeigninfoDao infoDao;

	public void pageWebMagic() {
		boolean flag = true;
		while (flag) {
			Pages Page = pageDao.queryPageState();
			if (Page == null) {
				return;
			}
			String urlPage = UrlConstant.getUrlStart(Page.getId().toString());
			Spider.create(new WebMagicPageTest(Page, pageDao,infoDao)).addUrl(urlPage).thread(1).run();
		}
	}

	@Test
	public void infoWebMagic() {
		boolean flag = true;
		while (flag) {
			Foreigninfo notQuery = infoDao.queryIsQuerys();
			Spider.create(new WebMagicTest(notQuery, infoDao)).addUrl(notQuery.getUrls()).thread(1).run();
		}
	}

	public void info() throws Exception {
		boolean flag = true;
		while (flag) {
			Foreigninfo notQuery = infoDao.queryIsQuerys();
			if (notQuery == null) {
				return;
			}
			try {
				String urls = notQuery.getUrls();
				String html = HttpClientUtil.get(urls, HeaderConstant.getHeaderPage());
				HtmlUtil.infoAnalysis(urls, html, notQuery);
				notQuery.setIsquerys(1);
			} catch (Exception e) {
				notQuery.setIsquerys(-1);
			} finally {
				infoDao.save(notQuery);
			}
		}
		/*
		 * Foreigninfo notQuery = infoDao.findOne((long) 34); try { String urls
		 * = notQuery.getUrls(); String html =
		 * HttpClientUtil.get("http://www.zbgb.org/73/StandardDetail36671.htm",
		 * HeaderConstant.getHeaderPage()); HtmlUtil.infoAnalysis(urls, html,
		 * notQuery); System.out.println(notQuery.getBeReplacedStcode());
		 * System.out.println(notQuery.toString()); }catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	public void page() {
		boolean flag = true;
		while (flag) {
			Pages Page = pageDao.queryPageState();
			if (Page == null) {
				return;
			}
			try {
				String urlPage = UrlConstant.getUrlStart(Page.getId().toString());

				String html = HttpClientUtil.get(urlPage, HeaderConstant.getHeaderPage());

				List<String> urlList = HtmlUtil.getUrlList(html);

				List<Foreigninfo> entitys = new ArrayList<Foreigninfo>();
				for (String url : urlList) {
					Foreigninfo entity = new Foreigninfo();
					entity.setUrls(url);
					entity.setIsquerys(0);
					entitys.add(entity);
				}
				infoDao.save(entitys);
				Page.setState(1);
			} catch (Exception e) {
				e.printStackTrace();
				Page.setState(-1);
			} finally {
				pageDao.save(Page);
			}

		}
	}

	public void TestWebConnetion() {
		long startTime = System.currentTimeMillis();
		int success = 0;
		int error = 0;

		for (Integer i = 200; i < 400; i++) {// 解决页面page问题
			try {
				String html = HttpClientUtil.get(UrlConstant.getUrlStart(i.toString()), HeaderConstant.getHeaderPage());
				if (null == html || html.equals(""))
					error++;
				else
					success++;
			} catch (Exception e) {
				e.printStackTrace();
				error++;
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("成功:" + success + "失败:" + error);
		System.out.println("一共花费了" + (endTime - startTime));
	}

	public void contextLoads() {
		int pageCount = 2476046;
		for (int i = 0; i < pageCount; i++) {// 解决页面page问题
			Pages page = new Pages();
			List<String> urlList = null;
			try {
				page.setState(0);
				pageDao.save(page);
				// 尝试获取url list
				String html = HttpClientUtil.get(UrlConstant.getUrlStart(page.getId().toString()),
						HeaderConstant.getHeaderPage());
				urlList = HtmlUtil.getUrlList(html);
			} catch (Exception e) {
				page.setState(1);
				pageDao.save(page);
				System.out.println("保存第" + i + "页时出错");
				e.printStackTrace();
				continue;
			}

			if (null == urlList) {
				page.setState(1);
				pageDao.save(page);
				System.out.println("保存第" + i + "页时出错");
				continue;
			}

			// 循环详细的页面url
			for (String urlDetails : urlList) {
				try {
					Foreigninfo info = new Foreigninfo();
					// 保存页面路径
					info.setUrls(UrlConstant.url + urlDetails);
					// 保存分类
					info.setTypes(StringUtil.getTypes(urlDetails));
					info.setIsquerys(0);
					infoDao.saveAndFlush(info);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("保存详情页面出错" + UrlConstant.url + urlDetails);
				}
			}
		}
	}

}
