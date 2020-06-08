package com.neuedu.onlearn.po;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 课程实体类
 * @author 11961
 *
 */
public class Course {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Integer id;
	private String courseName;
	private String courseWrapper;
	private String courseIntro;
	private Teacher teacher;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseWrapper() {
		return courseWrapper;
	}
	public void setCourseWrapper(String courseWrapper) {
		this.courseWrapper = courseWrapper;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
	
	
	
	
	
	/*
	if(updateTime == null) {
		return null;
	}
	String dateStr = sdf.format(updateTime);
	return dateStr;
	*/
}
