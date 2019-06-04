package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 组织管理实体
 * @author Administrator
 *
 */

public class Team implements Serializable {

	private Integer id; // 自增id
	private String teamName; // 组织姓名
	private String teamBrief; // 组织简介
	private String teamAnnoun; // 组织公告
	private String teamShine; // 组织风采
	private String teamProject; // 组织项目
	private String teamPersonCount; // 已有成员人数
	private String isGood;
    private String teamLogo;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamBrief() {
		return teamBrief;
	}
	public void setTeamBrief(String teamBrief) {
		this.teamBrief = teamBrief;
	}
	public String getTeamAnnoun() {
		return teamAnnoun;
	}
	public void setTeamAnnoun(String teamAnnoun) {
		this.teamAnnoun = teamAnnoun;
	}
	public String getTeamShine() {
		return teamShine;
	}
	public void setTeamShine(String teamShine) {
		this.teamShine = teamShine;
	}
	public String getTeamProject() {
		return teamProject;
	}
	public void setTeamProject(String teamProject) {
		this.teamProject = teamProject;
	}
	public String getTeamPersonCount() {
		return teamPersonCount;
	}
	public void setTeamPersonCount(String teamPersonCount) {
		this.teamPersonCount = teamPersonCount;
	}
	public String getIsGood() {
		return isGood;
	}
	public void setIsGood(String isGood) {
		this.isGood = isGood;
	}
	public String getTeamLogo() {
		return teamLogo;
	}
	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", teamBrief="
				+ teamBrief + ", teamAnnoun=" + teamAnnoun + ", teamShine="
				+ teamShine + ", teamProject=" + teamProject
				+ ", teamPersonCount=" + teamPersonCount + ", isGood=" + isGood
				+ ", teamLogo=" + teamLogo + "]";
	}
	
    
	
	
}
