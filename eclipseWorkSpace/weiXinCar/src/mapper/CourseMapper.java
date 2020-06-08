package com.neuedu.onlearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.onlearn.po.Course;


public interface CourseMapper {
	
	/**
	 * 添加课程
	 * @param course
	 */
	void addCourse(Course course);
	/**
	 * 返回符合条件的记录数量
	 * @param keyword
	 * @return
	 */
	int getCourseByKeywordCount(@Param("keyword")String keyword);
	/**
	 * 按条件分页查询
	 * @param begin
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	List<Course> getCourseByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize, @Param("keyword")String keyword);
	
	
	
	
	/**
	 * 通过课程名查找课程
	 * @param coursename
	 * @return
	 */
	Course findCourseByName(String coursename);
	/**
	 * 通过id查找课程信息
	 * @param id
	 * @return
	 */
 	Course findCourseById(Integer id);
	
	
	
}
