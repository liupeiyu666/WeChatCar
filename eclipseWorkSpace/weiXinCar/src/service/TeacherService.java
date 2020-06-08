package com.neuedu.onlearn.service;

import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.web.Page;

public interface TeacherService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 */
	Teacher login(String username,String password);
	/**
	 *添加教师
	 * @param teacher
	 */
	void addTeacher(Teacher teacher);
	/**
	 * 删除教师
	 * @param id
	 */
	void deleteTeacher(Integer id);
	/**
	 * 更新教师信息
	 * @param teacher
	 */
	void updateTeacher(Teacher teacher);
	/**
	 * 分页查询
	 * @param pageNum 页码
	 * @param pageSize 每页大小
	 * @param keyword 关键字
	 */
	Page<Teacher> list(int pageNum,int pageSize,String keyword);
	/**
	 * 通过id查找教师信息
	 * @param id
	 * @return
	 */
	Teacher findTeacherById(Integer id);
}
