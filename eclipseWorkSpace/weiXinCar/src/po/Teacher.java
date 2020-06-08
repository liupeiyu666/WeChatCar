package com.neuedu.onlearn.po;

import java.text.SimpleDateFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//json初始化时取消空值null
@JsonInclude(Include.NON_NULL)
public class Teacher{
	//设置时间格式
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Integer id;
	private String nickName;
	private Integer gender;
	private String role;
	private String picture;
	private String address;
	private String realName;
	private String degree;
	private String title;
	private String tel;
	private String password;
	private Integer status;
	private String experience;
	private String email;
	private Date createTime;
	private Date updateTime;
	//alt+shift+s+ getters and setters 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreateTime() {
		if(createTime == null) {
			return null;
		}
		String dateStr = sdf.format(createTime);
		return dateStr;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		if(updateTime == null) {
			return null;
		}
		String dateStr = sdf.format(updateTime);
		return dateStr;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", nickName=" + nickName + ", gender=" + gender + ", role=" + role + ", picture="
				+ picture + ", address=" + address + ", realName=" + realName + ", degree=" + degree + ", title="
				+ title + ", tel=" + tel + ", password=" + password + ", status=" + status + ", experience="
				+ experience + ", email=" + email + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	

}
