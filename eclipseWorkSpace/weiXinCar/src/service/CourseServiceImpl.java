package com.neuedu.onlearn.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.onlearn.mapper.CourseMapper;
import com.neuedu.onlearn.mapper.TeacherMapper;
import com.neuedu.onlearn.po.Course;
import com.neuedu.onlearn.util.D;
import com.neuedu.onlearn.web.Page;

public class CourseServiceImpl implements CourseService{
	private CourseMapper courseMapper; 
	public CourseServiceImpl() {
		SqlSession session = D.getConn();
		courseMapper = session.getMapper(CourseMapper.class);//获取mapper
	}
	/**
	 * 添加课程信息
	 */
	public void addCourse(Course course) {
		courseMapper.addCourse(course);
	}
	/**
	 * 查询课程
	 */
	public Page<Course> list(int pageNum, int pageSize, String keyword) {
		int total = courseMapper.getCourseByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<Course> datas = courseMapper.getCourseByKeyword(begin,pageSize,keyword);
		
		Page<Course> pageData = new Page<Course>(datas,total,pageSize,pageNum);
		
		return pageData;
	}
	
	
	
	
	
	/**
	 * 通过id查找课程信息
	 */
	public Course findCourseById(Integer id) {
		return courseMapper.findCourseById(id);
	}

}
