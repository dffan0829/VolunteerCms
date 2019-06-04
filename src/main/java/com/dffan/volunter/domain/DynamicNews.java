package com.dffan.volunter.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 动态要闻实体
 * 
 * @author Administrator
 * 
 */

@Alias("dynamicNews")
public class DynamicNews implements Serializable {

	private Integer id; // 自增的id
	private String dynamicTitle; // 动态新闻标题
	private String dynamicContent; // 动态新闻内容
	private String dynamicTime;//发布时间
	private String dynamicImages;//图片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDynamicTitle() {
		return dynamicTitle;
	}

	public void setDynamicTitle(String dynamicTitle) {
		this.dynamicTitle = dynamicTitle;
	}

	public String getDynamicContent() {
		return dynamicContent;
	}

	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public String getDynamicTime() {
		return dynamicTime;
	}

	public void setDynamicTime(String dynamicTime) {
		this.dynamicTime = dynamicTime;
	}

	public String getDynamicImages() {
		return dynamicImages;
	}

	public void setDynamicImages(String dynamicImages) {
		this.dynamicImages = dynamicImages;
	}

	@Override
	public String toString() {
		return "DynamicNews [id=" + id + ", dynamicTitle=" + dynamicTitle
				+ ", dynamicContent=" + dynamicContent + ", dynamicTime="
				+ dynamicTime + ", dynamicImages=" + dynamicImages + "]";
	}

	public DynamicNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DynamicNews(String dynamicTitle, String dynamicContent,
			String dynamicTime) {
		super();
		this.dynamicTitle = dynamicTitle;
		this.dynamicContent = dynamicContent;
		this.dynamicTime = dynamicTime;
	}

	public DynamicNews(Integer id, String dynamicTitle, String dynamicContent) {
		super();
		this.id = id;
		this.dynamicTitle = dynamicTitle;
		this.dynamicContent = dynamicContent;
	}


	
	
	

}
