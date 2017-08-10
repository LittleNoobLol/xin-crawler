package com.foreign.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class Foreigninfo {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 类型
	 */
	private Integer types;

	/**
	 * 规格
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String stcode;

	/**
	 * 英文名字
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String englishName;
	
	/**
	 * 中文名称
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String chinahName;
	

	/**
	 * 首次发布日期
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String firstPublishDate;

	/**
	 * 代替标准号
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String takeOfStcode;

	/**
	 * 被代替标准号
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String beReplacedStcode;

	/**
	 * 采用国际标准编号
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String useInternationalStcode;

	/**
	 * 全文语种
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String language;

	/**
	 * 发布日期
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String publishDate;

	/**
	 * 标准类别
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String standardClass;

	/**
	 * 国际标准分类法
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String ics;

	/**
	 * 标准页数
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String pages;

	/**
	 * 引用标准
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String referenceStandard;

	/**
	 * 网页url
	 */
	@Lob
	@Column(columnDefinition = "TEXT")
	private String urls;

	/**
	 * 是否查询过
	 */
	@NotNull
	@ColumnDefault("0")
	private Integer isquerys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public String getStcode() {
		return stcode;
	}

	public void setStcode(String stcode) {
		this.stcode = stcode;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFirstPublishDate() {
		return firstPublishDate;
	}

	public void setFirstPublishDate(String firstPublishDate) {
		this.firstPublishDate = firstPublishDate;
	}

	public String getTakeOfStcode() {
		return takeOfStcode;
	}

	public void setTakeOfStcode(String takeOfStcode) {
		this.takeOfStcode = takeOfStcode;
	}

	public String getBeReplacedStcode() {
		return beReplacedStcode;
	}

	public void setBeReplacedStcode(String beReplacedStcode) {
		this.beReplacedStcode = beReplacedStcode;
	}

	public String getUseInternationalStcode() {
		return useInternationalStcode;
	}

	public void setUseInternationalStcode(String useInternationalStcode) {
		this.useInternationalStcode = useInternationalStcode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getStandardClass() {
		return standardClass;
	}

	public void setStandardClass(String standardClass) {
		this.standardClass = standardClass;
	}

	public String getIcs() {
		return ics;
	}

	public void setIcs(String ics) {
		this.ics = ics;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getReferenceStandard() {
		return referenceStandard;
	}

	public void setReferenceStandard(String referenceStandard) {
		this.referenceStandard = referenceStandard;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public Integer getIsquerys() {
		return isquerys;
	}

	public void setIsquerys(Integer isquerys) {
		this.isquerys = isquerys;
	}

	
	public String getChinahName() {
		return chinahName;
	}

	public void setChinahName(String chinahName) {
		this.chinahName = chinahName;
	}

	@Override
	public String toString() {
		return "Foreigninfo [id=" + id + ", types=" + types + ", stcode=" + stcode + ", englishName=" + englishName
				+ ", firstPublishDate=" + firstPublishDate + ", takeOfStcode=" + takeOfStcode + ", beReplacedStcode="
				+ beReplacedStcode + ", useInternationalStcode=" + useInternationalStcode + ", language=" + language
				+ ", publishDate=" + publishDate + ", standardClass=" + standardClass + ", ics=" + ics + ", pages="
				+ pages + ", referenceStandard=" + referenceStandard + ", urls=" + urls + ", isquerys=" + isquerys
				+ "]";
	}

	
}
