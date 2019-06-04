package com.dffan.volunter.domain;


/**
 * 志愿者加入活动的实体类
 * @author Administrator
 *
 */

public class AddProject {

	private Integer id;
	private Integer proId;//活动的id
	private Integer volunteerId;//志愿者的id
	
	public AddProject(Integer proId, Integer volunteerId) {
		this.proId = proId;
		this.volunteerId = volunteerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(Integer volunteerId) {
		this.volunteerId = volunteerId;
	}
	
}
