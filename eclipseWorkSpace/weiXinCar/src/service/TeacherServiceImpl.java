package com.neuedu.onlearn.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.mapper.TeacherMapper;
import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.util.D;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.util.Md5Util;
import com.neuedu.onlearn.web.Page;

public class TeacherServiceImpl implements TeacherService{
	private TeacherMapper teacherMapper;
	public TeacherServiceImpl() {
		SqlSession session = D.getConn();
		teacherMapper = session.getMapper(TeacherMapper.class);
	}
	/**
	 * 用户
	 */
	public Teacher login(String username, String password){
		Teacher teacher = teacherMapper.findTeacherByTel(username);
		if(teacher == null) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_INFO);
		}
		//md5加密
		String enccodePwd = Md5Util.encode(password);
		//密码不相等
		if(!teacher.getPassword().equalsIgnoreCase(enccodePwd)) {
			throw new InvalidParamException(E.USER_INFO_ERROR_CODE,E.USER_INFO_ERROR_INFO);
		}
		return teacher;
	}
	/**
	 * 添加教师信息
	 */
	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		teacher.setRole(Global.ROLE_TEACHER);
		String encodePwd = Md5Util.encode(teacher.getPassword());
		teacher.setPassword(encodePwd);
		teacherMapper.addTeacher(teacher);
	}
	/**
	 * 删除教师（逻辑删除）
	 */
	public void deleteTeacher(Integer id) {
		Teacher existTeacher = teacherMapper.findTeacherById(id);
		if(existTeacher == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "该用户不存在");
		}
		Teacher teacher =new Teacher();
		teacher.setId(id);
		teacher.setStatus(Global.USER_STATUS_OFF);
		teacherMapper.updateTeacher(teacher);
		
	}
	/**
	 * 更改教师信息
	 */
	public void updateTeacher(Teacher teacher) {
		Teacher existTeacher = teacherMapper.findTeacherById(teacher.getId());
		if(existTeacher == null) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "该用户不存在");
		}
		teacherMapper.updateTeacher(teacher);
		
	}
	/**
	 * 分页显示教师信息
	 * @return 
	 */
	public Page<Teacher> list(int pageNum, int pageSize, String keyword) {
		int total = teacherMapper.getTeacherByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<Teacher> datas = teacherMapper.getTeacherByKeyword(begin,pageSize,keyword);
		
		Page<Teacher> pageData = new Page<Teacher>(datas,total,pageSize,pageNum);
		
		return pageData;
		
		
	}
	/**
	 * 根据id查找教师
	 */
	public Teacher findTeacherById(Integer id) {
		return teacherMapper.findTeacherById(id);
	}

}










