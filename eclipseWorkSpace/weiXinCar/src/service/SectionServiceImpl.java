package com.neuedu.onlearn.service;

import java.util.List;

import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.mapper.SectionMapper;
import com.neuedu.onlearn.po.Section;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.web.Page;

public class SectionServiceImpl implements SectionService{
	private SectionMapper sectionMapper;
	/**
	 * 添加小节
	 */
	public void addSection(Section section) {
		sectionMapper.addSection(section);
	}
	/**
	 * 通过id查找小节
	 */
	public Section findSectionById(Integer id) {
		return sectionMapper.findSectionById(id);
	}
	/**
	 * 删除小节
	 */
	public void deleteSection(Integer id) {
		Section existSection = sectionMapper.findSectionById(id);
		if(existSection == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "该小节不存在");
		}
		Section section =new Section();
		section.setId(id);
		section.setStatus(Global.USER_STATUS_OFF);
		sectionMapper.updateSection(section);
		
	}
	/**
	 * 更新小节
	 */
	public void updateSection(Section section) {
		Section existSection = sectionMapper.findSectionById(section.getId());
		if(existSection == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "该小节不存在");
		}
		sectionMapper.updateSection(section);
		
	}
	/**
	 * 分页显示数据
	 */
	public Page<Section> list(int pageNum, int pageSize, String keyword) {
		int total = sectionMapper.getSectionByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<Section> datas = sectionMapper.getSectionByKeyword(begin,pageSize,keyword);
		
		Page<Section> pageData = new Page<Section>(datas,total,pageSize,pageNum);
		
		return pageData;
	}

}
