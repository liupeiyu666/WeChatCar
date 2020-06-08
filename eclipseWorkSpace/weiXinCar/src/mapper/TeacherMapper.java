package com.neuedu.onlearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.onlearn.po.Teacher;

public interface TeacherMapper {
	/**
	 * 根据电话号查找用户
	 * @param username
	 * @return
	 */
	Teacher findTeacherByTel(String username);
	/**
	 * 通过Id查找教师
	 * @param id
	 * @return
	 */
	Teacher findTeacherById(Integer id);
	/**
	 * 添加教师
	 * @param teacher
	 */
	void addTeacher(Teacher teacher);
	
	/**
	 * 修改教师信息
	 * @param teacher
	 */
	int updateTeacher(Teacher teacher);
	/**
	 * 返回符合条件的记录数量
	 * @param keyword
	 * @return
	 */
	int getTeacherByKeywordCount(@Param("keyword")String keyword);
	/**
	 * 按条件分页查询
	 * @param begin
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	List<Teacher> getTeacherByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
