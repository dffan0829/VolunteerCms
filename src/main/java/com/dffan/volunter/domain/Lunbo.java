package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 轮播图entity
 * 
 * @author admin
 * 
 */
public class Lunbo implements Serializable {
	
	private Integer id;
	private String lunTitle;// 轮播的标题
	private String lunImage;// 轮播的id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLunTitle() {
		return lunTitle;
	}

	public void setLunTitle(String lunTitle) {
		this.lunTitle = lunTitle;
	}

	public String getLunImage() {
		return lunImage;
	}

	public void setLunImage(String lunImage) {
		this.lunImage = lunImage;
	}

}
