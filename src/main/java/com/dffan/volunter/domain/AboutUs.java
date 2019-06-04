package com.dffan.volunter.domain;

/**
 * 关于我们
 * @author Administrator
 *
 */
public class AboutUs {

	private Integer id;
	private String about_title;
	private String about_content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAbout_title() {
		return about_title;
	}
	public void setAbout_title(String about_title) {
		this.about_title = about_title;
	}
	public String getAbout_content() {
		return about_content;
	}
	public void setAbout_content(String about_content) {
		this.about_content = about_content;
	}
	public AboutUs(Integer id,String about_title, String about_content) {
		super();
		this.id = id;
		this.about_title = about_title;
		this.about_content = about_content;
	}
	public AboutUs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
