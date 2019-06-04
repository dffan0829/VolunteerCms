package com.dffan.volunter.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 各队发布项目
 * 
 * @author Administrator
 * 
 */
@Alias("teamProject")
public class TeamProject implements Serializable{

	private Integer id;// 自增的id
	private String proPubteam;// 项目类型
	private String proContent;// 项目内容
	private String proStatus;// 项目状态  0:未审核 1：已审核(招募中) 2：进行中（到了活动的时间） 3：已完成（过了活动的时间）
	private String proAddress;// 项目活动地址
	private String proTime;
	private String proGotime;//活动的时间
	private String proFkid;
	private String proTitle;
	private Integer proTotalperson;
	private Integer proAddcount;//加入该活动的参与人数
	
	public Integer getProAddcount() {
		return proAddcount;
	}

	public void setProAddcount(Integer proAddcount) {
		this.proAddcount = proAddcount;
	}

	public String getProGotime() {
		return proGotime;
	}

	public void setProGotime(String proGotime) {
		this.proGotime = proGotime;
	}

	public Integer getProTotalperson() {
		return proTotalperson;
	}

	public void setProTotalperson(Integer proTotalperson) {
		this.proTotalperson = proTotalperson;
	}

	public void setProFkid(String proFkid) {
		this.proFkid = proFkid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProPubteam() {
		return proPubteam;
	}

	public void setProPubteam(String proPubteam) {
		this.proPubteam = proPubteam;
	}

	public String getProContent() {
		return proContent;
	}

	public void setProContent(String proContent) {
		this.proContent = proContent;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getProAddress() {
		return proAddress;
	}

	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}

	public String getProTime() {
		return proTime;
	}

	public void setProTime(String proTime) {
		this.proTime = proTime;
	}

	public String getProFkid() {
		return proFkid;
	}

	public void setProFckid(String proFkid) {
		this.proFkid = proFkid;
	}

	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}

	public TeamProject() {
		super();
	}

	@Override
	public String toString() {
		return "TeamProject [id=" + id + ", proPubteam=" + proPubteam
				+ ", proContent=" + proContent + ", proStatus=" + proStatus
				+ ", proAddress=" + proAddress + ", proTime=" + proTime
				+ ", proGotime=" + proGotime + ", proFkid=" + proFkid
				+ ", proTitle=" + proTitle + ", proTotalperson="
				+ proTotalperson + "]";
	}
	
	
	

}
