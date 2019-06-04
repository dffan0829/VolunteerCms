package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 管理员回复Entity
 * @author admin
 *
 */
public class Reply implements Serializable {
	
    private Integer id;
    private String ip;
    private String replyContent;//回复内容
    private String replyTime;
    private Integer fkId;//留言表的id
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getFkId() {
		return fkId;
	}
	public void setFkId(Integer fkId) {
		this.fkId = fkId;
	}
	
	public Reply(String ip, String replyContent, String replyTime, Integer fkId) {
		super();
		this.ip = ip;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.fkId = fkId;
	}
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reply [ip=" + ip + ", replyContent=" + replyContent
				+ ", replyTime=" + replyTime + ", fkId=" + fkId + "]";
	}

	

    
}
