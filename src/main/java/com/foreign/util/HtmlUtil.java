package com.foreign.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.foreign.pojo.Foreigninfo;

public class HtmlUtil {

	/**
	 * 解析详情页面
	 * 
	 * @param url
	 * @param html
	 * @param entity
	 */
	public static void infoAnalysis(String url, Document doc, Foreigninfo entity) {
		String temp = url.substring(url.indexOf("zbgb.org/"), url.lastIndexOf('/'));
		String type = temp.substring(9, temp.length());
		entity.setTypes(Integer.valueOf(type));

		Elements select = doc.select(".newsdetail .newsdetail_content .oneRow");
		for (Element oneRow : select) {
			String tagName = oneRow.select(".tagName").text();
			switch (tagName) {
			case "标准编号：":
				entity.setStcode(oneRow.ownText());
				break;
			case "中文标准名称：":
				entity.setChinahName(oneRow.ownText());
				break;
			case "英文标准名称：":
				entity.setEnglishName(oneRow.ownText());
				break;
			case "首次发布日期：":
				entity.setFirstPublishDate(oneRow.ownText());
				break;
			case "代替标准号：":
				String takeOfStcode = StringUtil.getInnerText(oneRow.text(), "代替标准号：".length());
				entity.setTakeOfStcode(takeOfStcode);
				break;
			case "被代替标准号：":
				String beReplacedStcode = StringUtil.getInnerText(oneRow.text(), "被代替标准号：".length());
				entity.setBeReplacedStcode(beReplacedStcode);
				break;
			case "采用国际标准编号：":
				String useInternationalStcode = StringUtil.getInnerText(oneRow.text(), "采用国际标准编号：".length());
				entity.setUseInternationalStcode(useInternationalStcode);
				break;
			case "语言：":
				entity.setLanguage(oneRow.ownText());
				break;
			case "发布日期：":
				entity.setPublishDate(oneRow.ownText());
				break;
			case "标准类别：":
				String standardClass = StringUtil.getInnerText(oneRow.text(), "标准类别：".length());
				entity.setStandardClass(standardClass);
				break;
			case "国际标准分类法(ICS)：":
				String ics = StringUtil.getInnerText(oneRow.text(), "国际标准分类法(ICS)：".length());
				entity.setIcs(ics);
				break;
			case "标准页数：":
				entity.setPages(oneRow.ownText());
				break;
			case "引用标准：":
				String referenceStandard = StringUtil.getInnerText(oneRow.text(), "引用标准：".length());
				entity.setReferenceStandard(referenceStandard);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 解析详情页面
	 * 
	 * @param url
	 * @param html
	 * @param entity
	 */
	public static void infoAnalysis(String url, String html, Foreigninfo entity) {
		String temp = url.substring(url.indexOf("zbgb.org/"), url.lastIndexOf('/'));
		String type = temp.substring(9, temp.length());
		entity.setTypes(Integer.valueOf(type));

		Document doc = Jsoup.parse(html);
		Elements select = doc.select(".newsdetail .newsdetail_content .oneRow");
		for (Element oneRow : select) {
			String tagName = oneRow.select(".tagName").text();
			switch (tagName) {
			case "标准编号：":
				entity.setStcode(oneRow.ownText());
				break;
			case "中文标准名称：":
				entity.setChinahName(oneRow.ownText());
				break;
			case "英文标准名称：":
				entity.setEnglishName(oneRow.ownText());
				break;
			case "首次发布日期：":
				entity.setFirstPublishDate(oneRow.ownText());
				break;
			case "代替标准号：":
				String takeOfStcode = StringUtil.getInnerText(oneRow.text(), "代替标准号：".length());
				entity.setTakeOfStcode(takeOfStcode);
				break;
			case "被代替标准号：":
				String beReplacedStcode = StringUtil.getInnerText(oneRow.text(), "被代替标准号：".length());
				entity.setBeReplacedStcode(beReplacedStcode);
				break;
			case "采用国际标准编号：":
				String useInternationalStcode = StringUtil.getInnerText(oneRow.text(), "采用国际标准编号：".length());
				entity.setUseInternationalStcode(useInternationalStcode);
				break;
			case "语言：":
				entity.setLanguage(oneRow.ownText());
				break;
			case "发布日期：":
				entity.setPublishDate(oneRow.ownText());
				break;
			case "标准类别：":
				String standardClass = StringUtil.getInnerText(oneRow.text(), "标准类别：".length());
				entity.setStandardClass(standardClass);
				break;
			case "国际标准分类法(ICS)：":
				String ics = StringUtil.getInnerText(oneRow.text(), "国际标准分类法(ICS)：".length());
				entity.setIcs(ics);
				break;
			case "标准页数：":
				entity.setPages(oneRow.ownText());
				break;
			case "引用标准：":
				String referenceStandard = StringUtil.getInnerText(oneRow.text(), "引用标准：".length());
				entity.setReferenceStandard(referenceStandard);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 解析页面的url
	 * 
	 * @param html
	 * @return list的url
	 * @throws Exception
	 */
	public static List<String> getUrlList(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		Elements urls = doc.select(".content .top2 .g2 a");
		List<String> list = new ArrayList<String>();
		for (Element element : urls) {
			list.add(element.attr("href").toString());
		}
		return list;
	}
	
	/**
	 * 解析页面的url
	 * 
	 * @param html
	 * @return list的url
	 * @throws Exception
	 */
	public static List<String> getUrlList(Document doc) throws Exception {
		Elements urls = doc.select(".content .top2 .g2 a");
		List<String> list = new ArrayList<String>();
		for (Element element : urls) {
			list.add(element.attr("href").toString());
		}
		return list;
	}

	/**
	 * 解析页面的url,被弃用,原因page参数过大访问无响应,更换新url
	 * 
	 * @param html
	 * @return list的url
	 * @throws Exception
	 */
	public static List<String> getUrlListOld(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		Elements urls = doc.select(".newslist_box .newslist_box_list a");
		List<String> list = new ArrayList<String>();
		for (Element element : urls) {
			list.add(element.attr("href").toString());
		}
		return list;
	}
}
