package com.neuedu.onlearn.service;

import com.neuedu.onlearn.po.Section;

import com.neuedu.onlearn.web.Page;


public interface SectionService {
	/**
	 * 通过id查找小节
	 * @param id
	 * @return
	 */
	Section findSectionById(Integer id);
	
	
	
	/**
	 * 添加小节
	 * @param section
	 */
	public void addSection(Section section);
	/**
	 * 删除小节
	 * @param id
	 */
	void deleteSection(Integer id);
	/**
	 * 更新小节
	 * @param section
	 */
	void updateSection(Section section);
	
	/**
	 * 分页查询
	 * @param pageNum 页码
	 * @param pageSize 每页大小
	 * @param keyword 关键字
	 */
	Page<Section> list(int pageNum,int pageSize,String keyword);
}
