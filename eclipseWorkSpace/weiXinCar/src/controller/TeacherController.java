package com.neuedu.onlearn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.onlearn.exception.BaseException;
import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.service.TeacherService;
import com.neuedu.onlearn.service.TeacherServiceImpl;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.SessionManager;
import com.neuedu.onlearn.util.V;
import com.neuedu.onlearn.web.ApiResult;
import com.neuedu.onlearn.web.Page;


public class TeacherController{
	private TeacherService teacherService;
	public TeacherController() {
		this.teacherService = new TeacherServiceImpl();
	}
	
	/**
	 * 教师登陆
	 * @param req
	 * @param resp
	 * @return
	 */
	public Map<String,String> login(HttpServletRequest req, HttpServletResponse resp){
		//验证用户
		String[] params = {"login_name","password"};
		V.valid(req,params);
		
		String username = req.getParameter("login_name");
		String password = req.getParameter("password");
		
		
			
		Teacher teacher = teacherService.login(username, password);
		HttpSession session = req.getSession();//创建session
		session.setAttribute("userInfo", teacher);//用户信息存入session
		//将session保存起来
		SessionManager.saveSession(session);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("token", session.getId());
		return map;
	} 
	/**
	 * 添加教师
	 * @param req
	 * @param resp
	 */
	public void add(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = {"tel","password","real_name","title","gender","email","degree","experience"};
		V.valid(req, params);
		Teacher teacher = V.entity(req,Teacher.class,params);
		teacherService.addTeacher(teacher);
		
	}
	/**
	 * 删除指定Id的教师
	 * @param req
	 * @param resp
	 */
	public void delete(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = {"id"};
		V.valid(req, params);
		
		int id = Integer.parseInt(req.getParameter("id"));
		teacherService.deleteTeacher(id);
	}
	/**
	 * 更新教师信息
	 * @param req
	 * @param resp
	 */
	public void update(HttpServletRequest req,HttpServletResponse resp) {
		//验证参数
		String[] mastParam = {"id"};
		V.valid(req, mastParam);
		//接收参数
		String[] params = {"id","tel","password","real_name","title","gender","email","degree","experience"};
		Teacher teacher = V.entity(req, Teacher.class, params);
		teacherService.updateTeacher(teacher);
	}
	/**
	 * 分页显示数据
	 * @param req
	 * @param resp
	 */
	public Page<Teacher> list(HttpServletRequest req,HttpServletResponse resp) {
		String pageNumStr= req.getParameter("page_num") == null? "1" : req.getParameter("page_num");
		String pageSizeStr = req.getParameter("page_size") == null? "10" : req.getParameter("page_size");
		String keyword= req.getParameter("keyword") == null ? "" :req.getParameter("keyword");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Teacher> data = teacherService.list(pageNum, pageSize, keyword);
		return data;
	}
	/**
	 * 通过id查找教师信息
	 * @param req
	 * @param resp
	 * @return
	 */
	public Teacher find(HttpServletRequest req,HttpServletResponse resp) {
		String[] musParam = {"id"};
		V.valid(req, musParam);
		Integer id = Integer.parseInt(req.getParameter("id"));
		Teacher teacher = teacherService.findTeacherById(id);
		return teacher;
	}
}





















