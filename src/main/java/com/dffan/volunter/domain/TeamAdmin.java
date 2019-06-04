package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 各队实体
 * @author Administrator
 * 
 */
public class TeamAdmin implements Serializable {

	private Integer id;// 自增id
	private String teausername;// 各队用户名
	private String teapassword;// 各队密码
	private String teamtype;// 所属队的类别

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeausername() {
		return teausername;
	}

	public void setTeausername(String teausername) {
		this.teausername = teausername;
	}

	public String getTeapassword() {
		return teapassword;
	}

	public void setTeapassword(String teapassword) {
		this.teapassword = teapassword;
	}

	public String getTeamtype() {
		return teamtype;
	}

	public void setTeamtype(String teamtype) {
		this.teamtype = teamtype;
	}

	public TeamAdmin(Integer id, String teausername, String teapassword,
			String teamtype) {
		super();
		this.id = id;
		this.teausername = teausername;
		this.teapassword = teapassword;
		this.teamtype = teamtype;
	}

	public TeamAdmin() {
		super();
	}

}
