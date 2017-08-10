package com.foreign.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UrlPojo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 网页url
	 */
	@Column(length = 800)
	private String urls;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
	
	
}
