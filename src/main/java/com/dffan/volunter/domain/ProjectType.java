package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 项目类别实体
 * 
 * @author Administrator
 * 
 */
public class ProjectType implements Serializable {

	private Integer id;// 自增的id
	private String typeName;// 类别名称


	public ProjectType(Integer id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}

	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getTypeName() {
		return typeName;
	}




	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}




	public ProjectType() {
		super();
	}

}
