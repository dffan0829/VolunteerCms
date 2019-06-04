package com.dffan.volunter.domain;

import java.io.Serializable;

/**
 * 我的事项
 * @author admin
 *
 */
public class Mdsx implements Serializable {
  
	private Integer id;
	private String sxmc;//事项名称鞥
	private String sxsj;//事项时间
	private String sxlx;//事项类型
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSxmc() {
		return sxmc;
	}
	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	public String getSxsj() {
		return sxsj;
	}
	public void setSxsj(String sxsj) {
		this.sxsj = sxsj;
	}
	
	public String getSxlx() {
		return sxlx;
	}
	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	public Mdsx(String sxmc, String sxsj) {
		super();
		this.sxmc = sxmc;
		this.sxsj = sxsj;
	}
	public Mdsx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
