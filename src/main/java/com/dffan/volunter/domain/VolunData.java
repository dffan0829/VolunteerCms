package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 	在线培训 
 *  文件资料
 *  媒体传真
 * @author admin
 *
 */
public class VolunData implements Serializable {

	private Integer id;
	private String dataTitle;
	private String dataContent;
	private String dataTime;
	private String dataType;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDataTitle() {
		return dataTitle;
	}


	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}


	public String getDataContent() {
		return dataContent;
	}


	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}


	public String getDataTime() {
		return dataTime;
	}


	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	public VolunData() {
		super();
	}


	public VolunData(String dataTitle, String dataContent, String dataTime,
			String dataType) {
		super();
		this.dataTitle = dataTitle;
		this.dataContent = dataContent;
		this.dataTime = dataTime;
		this.dataType = dataType;

	
	}


	public VolunData(Integer id, String dataTitle, String dataContent) {
		super();
		this.id = id;
		this.dataTitle = dataTitle;
		this.dataContent = dataContent;
	}

	
	
	
}
