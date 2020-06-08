package com.neuedu.onlearn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.onlearn.po.Course;
import com.neuedu.onlearn.po.Section;

import com.neuedu.onlearn.service.SectionService;
import com.neuedu.onlearn.util.V;
import com.neuedu.onlearn.web.Page;

public class SectionController {
	private SectionService sectionService;
	
	/**
	 * 通过id查找小节信息
	 * @param req
	 * @param resp
	 * @return
	 */
	public Section find(HttpServletRequest req,HttpServletResponse resp) {
		String[] musParam = {"id"};
		V.valid(req, musParam);
		Integer id = Integer.parseInt(req.getParameter("id"));
		Section section = sectionService.findSectionById(id);
		return section;
	}
	
	/**
	 * 删除指定Id的小节
	 * @param req
	 * @param resp
	 */
	public void delete(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = {"id"};
		V.valid(req, params);
		
		int id = Integer.parseInt(req.getParameter("id"));
		sectionService.deleteSection(id);
	}
	/**
	 * 更新小节信息
	 * @param req
	 * @param resp
	 */
	public void update(HttpServletRequest req,HttpServletResponse resp) {
		//验证参数
		String[] mastParam = {"id"};
		V.valid(req, mastParam);
		//接收参数
		String[] params = {"id","course_id","teacher_id","title","title_wrapper","ordered","content","audio","status","video","create_by"};
		Section section = V.entity(req, Section.class, params);
		sectionService.updateSection(section);
	}
	
	/**
	 * 分页显示数据
	 * @param req
	 * @param resp
	 */
	public Page<Section> list(HttpServletRequest req,HttpServletResponse resp) {
		String pageNumStr= req.getParameter("page_num") == null? "1" : req.getParameter("page_num");
		String pageSizeStr = req.getParameter("page_size") == null? "10" : req.getParameter("page_size");
		String keyword= req.getParameter("keyword") == null ? "" :req.getParameter("keyword");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Section> data = sectionService.list(pageNum, pageSize, keyword);
		return data;
	}
	
	/**
	 * 添加课程
	 * @param req
	 * @param resp
	 */
	public void add(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = {"title","teacher_id","ordered","content"};
		V.valid(req, params);
		Section section = V.entity(req,Section.class,params);
		sectionService.addSection(section);
	}
}
