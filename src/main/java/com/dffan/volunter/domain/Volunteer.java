package com.dffan.volunter.domain;


import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 志愿者注册对应的实体
 * @author Administrator
 *
 */

public class Volunteer implements Serializable{

	private Integer id; // 自增的id
	@NotEmpty
	private String name; // 姓名
	@NotEmpty
	private String password; // 登录密码
	private String gender; // 性别
	private String date; // 出生日期
	//private String major; // 专业
	//private String oldaddress; // 籍贯
	private String politicalstus; // 政治面貌
	private String national; // 民族
	private String degree; // 学历
	private String position; // 职位
	private String identicard; // 身份证
	private String address; // 现居地址
	private String volunteam; // 志愿服务团队
	private String graduateschool; // 毕业院校
	//private String hobby; // 特长，技能
	private String voluntime; // 志愿服务时间
	@Email
	private String email; // email
	private String mobilephone; // 手机
	private String homephone; // 固定电话
	private String qq; // qq
    private String workunit;
    private String regisstatus;//0:未审核 1：已审核
    private String regisTime;//注册时间
    
    
	public String getRegisTime() {
		return regisTime;
	}

	public void setRegisTime(String regisTime) {
		this.regisTime = regisTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

/*	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getOldaddress() {
		return oldaddress;
	}

	public void setOldaddress(String oldaddress) {
		this.oldaddress = oldaddress;
	}*/

	public String getPoliticalstus() {
		return politicalstus;
	}

	public void setPoliticalstus(String politicalstus) {
		this.politicalstus = politicalstus;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIdenticard() {
		return identicard;
	}

	public void setIdenticard(String identicard) {
		this.identicard = identicard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVolunteam() {
		return volunteam;
	}

	public void setVolunteam(String volunteam) {
		this.volunteam = volunteam;
	}

	public String getGraduateschool() {
		return graduateschool;
	}

	public void setGraduateschool(String graduateschool) {
		this.graduateschool = graduateschool;
	}

/*	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
*/
	public String getVoluntime() {
		return voluntime;
	}

	public void setVoluntime(String voluntime) {
		this.voluntime = voluntime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	
	
	
	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	
	
	
	public String getRegisstatus() {
		return regisstatus;
	}

	public void setRegisstatus(String regisstatus) {
		this.regisstatus = regisstatus;
	}

	public Volunteer() {
		super();
	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", name=" + name + ", password="
				+ password + ", gender=" + gender + ", date=" + date
				+ ", politicalstus=" + politicalstus + ", national=" + national
				+ ", degree=" + degree + ", position=" + position
				+ ", identicard=" + identicard + ", address=" + address
				+ ", volunteam=" + volunteam + ", graduateschool="
				+ graduateschool + ", voluntime=" + voluntime + ", email="
				+ email + ", mobilephone=" + mobilephone + ", homephone="
				+ homephone + ", qq=" + qq + ", workunit=" + workunit
				+ ", regisstatus=" + regisstatus + "]";
	}


	

	
	
	
}
