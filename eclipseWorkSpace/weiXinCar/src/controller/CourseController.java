package com.neuedu.onlearn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.onlearn.po.Course;
import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.service.CourseService;
import com.neuedu.onlearn.service.CourseServiceImpl;
import com.neuedu.onlearn.util.V;
import com.neuedu.onlearn.web.Page;
/**
 * 用来接收关于课程操作请求
 * @author 11961
 *
 */
public class CourseController {
	
	private CourseService courseService;
	public CourseController(){
		this.courseService = new CourseServiceImpl();
	}
	/**
	 * 添加课程
	 * @param req
	 * @param resp
	 */
	public void add(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = {"course_name","course_wrapper","course_intro"};
		V.valid(req, params);
		Course course = V.entity(req,Course.class,params);
		HttpSession session = req.getSession();//获取session
		Teacher teacher = (Teacher)session.getAttribute("userInfo");//获取教师信息
		course.setTeacher(teacher);
		courseService.addCourse(course);
	}
	/**
	 * 查询课程
	 * @param req
	 * @param resp
	 */
	public Page<Course> list(HttpServletRequest req,HttpServletResponse resp) {
		String pageNumStr= V.getValue(req, "page_num", "1");
		String pageSizeStr = V.getValue(req, "page_size", "10");
		String keyword=V.getValue(req, "keyword", "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Course> data = courseService.list(pageNum, pageSize, keyword);
		return data;
	}
	
	
	
	
	
	
	
	/**
	 * 通过id查找数据
	 * @param req
	 * @param resp
	 * @return
	 */
	public Course find(HttpServletRequest req,HttpServletResponse resp) {
		String[] musParam = {"id"};
		V.valid(req, musParam);
		Integer id = Integer.parseInt(req.getParameter("id"));
		Course course = courseService.findCourseById(id);
		
		return course;
	}
}
