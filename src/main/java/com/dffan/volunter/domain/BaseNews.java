package com.dffan.volunter.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 基层速递实体
 * 
 * @author Administrator
 * 
 */
@Alias("baseNews")
public class BaseNews implements Serializable {

	private Integer id; // 自增的id
	private String baseTitle;// 消息主题
	private String baseContent;// 消息内容
	private String baseFkid;// 发布队伍
	private String baseTime;// 发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBaseTitle() {
		return baseTitle;
	}

	public void setBaseTitle(String baseTitle) {
		this.baseTitle = baseTitle;
	}

	public String getBaseContent() {
		return baseContent;
	}

	public void setBaseContent(String baseContent) {
		this.baseContent = baseContent;
	}

	public String getBaseFkid() {
		return baseFkid;
	}

	public void setBaseFkid(String baseFkid) {
		this.baseFkid = baseFkid;
	}

	public String getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}

}
