package com.dffan.volunter.domain;



/**
 * 管理员实体
 * @author Administrator
 *
 */
public class Admin {

	private Integer id;//自增id
	private String adminName;//用户名
	private String adminPassword;//密码
	private String loginTime;//Email
    private String role;//所属角色
    private String email;//邮箱
    private String team;//所属队伍
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminName=" + adminName
				+ ", adminPassword=" + adminPassword + ", loginTime="
				+ loginTime + ", role=" + role + ", email=" + email + ", team="
				+ team + "]";
	}



    
    
}
