package com.neuedu.onlearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.onlearn.po.Section;

public interface SectionMapper {
	/**
	 * 通过标题查找小节
	 * @param title
	 * @return
	 */
	Section findSectionByTitle(String title);
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
	void addSection(Section section);
	/**
	 * 修改小节信息
	 * @param section
	 * @return
	 */
	int updateSection(Section section);
	
	/**
	 * 返回符合条件的记录数量
	 * @param keyword
	 * @return
	 */
	int getSectionByKeywordCount(@Param("keyword")String keyword);
	/**
	 * 按条件分页查询
	 * @param begin
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	List<Section> getSectionByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
	
}
