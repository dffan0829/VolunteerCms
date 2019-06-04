package com.dffan.volunter.domain;

import java.io.Serializable;



/**
 * 通知公告实体
 * @author Administrator
 *
 */
public class Inform implements Serializable {

	private Integer id;
	private String informTitle;
	private String informContent;
	private String informTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInformTitle() {
		return informTitle;
	}
	public void setInformTitle(String informTitle) {
		this.informTitle = informTitle;
	}
	public String getInformContent() {
		return informContent;
	}
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}
	public String getInformTime() {
		return informTime;
	}
	public void setInformTime(String informTime) {
		this.informTime = informTime;
	}



	 
}
