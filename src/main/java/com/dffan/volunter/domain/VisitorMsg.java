package com.dffan.volunter.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 互动空间 留言
 * 
 * @author Administrator
 * 
 */
public class VisitorMsg  implements Serializable{

	private Integer id;
	private String ip;
	@NotEmpty
	private String msgTitle;
	@NotEmpty
	private String msgContent;
	private String msgTime;
	@NotEmpty
	private String msgPhpnumber;
	@NotEmpty
	private String msgPerson;
	private String msgStatus;
	
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



	public String getMsgTitle() {
		return msgTitle;
	}



	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}



	public String getMsgContent() {
		return msgContent;
	}



	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}



	public String getMsgTime() {
		return msgTime;
	}



	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}



	public String getMsgPhpnumber() {
		return msgPhpnumber;
	}



	public void setMsgPhpnumber(String msgPhpnumber) {
		this.msgPhpnumber = msgPhpnumber;
	}



	public String getMsgPerson() {
		return msgPerson;
	}



	public void setMsgPerson(String msgPerson) {
		this.msgPerson = msgPerson;
	}



	public String getMsgStatus() {
		return msgStatus;
	}



	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}



	public VisitorMsg(Integer id, String ip, String msgTitle,
			String msgContent, String msgTime, String msgPhpnumber,
			String msgPerson, String msgStatus) {
		super();
		this.id = id;
		this.ip = ip;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
		this.msgPhpnumber = msgPhpnumber;
		this.msgPerson = msgPerson;
		this.msgStatus = msgStatus;
	}
	public VisitorMsg() {
		super();
	}

	@Override
	public String toString() {
		return "VisitorMsg [id=" + id + ", ip=" + ip + ", msgTitle=" + msgTitle
				+ ", msgContent=" + msgContent + ", msgTime=" + msgTime
				+ ", msgPhpnumber=" + msgPhpnumber + ", msgPerson=" + msgPerson
				+ ", msgStatus=" + msgStatus + "]";
	}
	
	
	
	
	
	

	
	
}
