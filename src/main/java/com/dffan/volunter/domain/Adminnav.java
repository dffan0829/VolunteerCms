package com.dffan.volunter.domain;

public class Adminnav {

	
	private Integer navid;
	private String navtitle;
	private String navurl;
	
	public Integer getNavid() {
		return navid;
	}
	public void setNavid(Integer navid) {
		this.navid = navid;
	}
	public String getNavtitle() {
		return navtitle;
	}
	public void setNavtitle(String navtitle) {
		this.navtitle = navtitle;
	}
	public String getNavurl() {
		return navurl;
	}
	public void setNavurl(String navurl) {
		this.navurl = navurl;
	}
	public Adminnav(Integer navid, String navtitle, String navurl) {
		super();
		this.navid = navid;
		this.navtitle = navtitle;
		this.navurl = navurl;
	}
	public Adminnav() {
		super();
	}
	
	
}
