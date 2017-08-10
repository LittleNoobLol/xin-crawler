package com.foreign.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foreign.constant.HeaderConstant;
import com.foreign.constant.UrlConstant;
import com.foreign.dao.ForeigninfoDao;
import com.foreign.dao.PagesDao;
import com.foreign.dao.UrlPojoDao;
import com.foreign.pojo.Foreigninfo;
import com.foreign.pojo.Pages;
import com.foreign.pojo.UrlPojo;
import com.foreign.util.HtmlUtil;
import com.foreign.util.HttpClientUtil;
import com.foreign.util.StringUtil;

@RestController
public class PageController {

	@Resource
	private PagesDao pageDao;

	@Resource
	private ForeigninfoDao infoDao;

	@Resource
	private UrlPojoDao urlDao;

	private Logger LOGGER = LoggerFactory.getLogger(PageController.class);

	@RequestMapping("/")
	public String index() {
		return "hello,this is index page";
	}

	@RequestMapping("/info")
	public String info() {
		boolean flag = true;
		while (flag) {
			Foreigninfo notQuery = infoDao.queryIsQuerys();
			if(notQuery==null){
				return "finish";
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
		return "QWQ";
	}

	@RequestMapping("/page")
	public String page() {
		boolean flag = true;
		while (flag) {
			Pages Page = pageDao.queryPageState();
			if (Page == null) {
				return "success";
			}
			try {
				String html = HttpClientUtil.get(UrlConstant.getUrlStart(Page.getId().toString()),
						HeaderConstant.getHeaderPage());

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
		return "page";
	}

	public Map<String, String> pageTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", "hello");
		HeaderConstant.initCookie(
				"UM_distinctid=15d9cb80f7d199-026ec2cdc22b6-5d4e211f-100200-15d9cb80f7f437; bdshare_firstime=1501572920666; ASP.NET_SessionId=5rgwl1bkkomiqcefebwymfe2; CNZZDATA4887169=cnzz_eid%3D1560053799-1501571140-%26ntime%3D1501598165");
		int pageCount = 75032;
		for (int i = 1; i <= pageCount; i++) {// 解决页面page问题
			Pages page = new Pages();
			List<String> urlList = null;
			try {
				page.setState(0);
				pageDao.save(page);

				// 如果抓取完毕了当前页面,则删除
				if (pageCount == page.getId()) {
					pageDao.delete(page);
					return map;
				}

				// 尝试获取url list
				String html = HttpClientUtil.get(UrlConstant.getUrlStart(page.getId().toString()),
						HeaderConstant.getHeaderPage());
				urlList = HtmlUtil.getUrlList(html);
			} catch (Exception e) {
				page.setState(2);
				pageDao.save(page);
				LOGGER.error("保存第" + i + "页时出错");
				continue;
			}

			if (null == urlList || urlList.size() == 0) {
				page.setState(2);
				pageDao.save(page);
				LOGGER.error("保存第" + i + "页时出错,解析数据出错,list数据为:" + urlList.size());
				continue;
			}

			// 保存数据
			page.setState(1);
			pageDao.save(page);

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
					LOGGER.error("保存详情页面出错" + UrlConstant.url + urlDetails);
					UrlPojo pojo = new UrlPojo();
					pojo.setUrls(UrlConstant.url + urlDetails);
					urlDao.saveAndFlush(pojo);
				}
			}
		}

		return map;
	}

}
