package com.neuedu.onlearn.service;

import com.neuedu.onlearn.po.Course;

import com.neuedu.onlearn.web.Page;

public interface CourseService {
	/**
	 * 添加课程信息
	 */
	void addCourse(Course course);
	/**
	 * 分页查询课程信息
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	Page<Course> list(int pageNum,int pageSize,String keyword);
	
	
	
	
	/**
	 * 通过id查找数据
	 * @param id
	 * @return
	 */
	Course findCourseById(Integer id);
}
