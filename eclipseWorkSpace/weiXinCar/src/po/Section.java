package com.neuedu.onlearn.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Section {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Integer id;
	private Integer courseId;
	private Integer teacherId;
	private String title;
	private String titleWrapper;
	private String ordered;
	private String content;
	private String audio;
	private Integer status;
	private String video;
	private String createBy;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleWrapper() {
		return titleWrapper;
	}
	public void setTitleWrapper(String titleWrapper) {
		this.titleWrapper = titleWrapper;
	}
	public String getOrdered() {
		return ordered;
	}
	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
	@Override
	public String toString() {
		return "Section [id=" + id + ", courseId=" + courseId + ", teacherId=" + teacherId + ", title=" + title
				+ ", titleWrapper=" + titleWrapper + ", ordered=" + ordered + ", content=" + content + ", audio="
				+ audio + ", status=" + status + ", video=" + video + ", createBy=" + createBy + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
	
}
