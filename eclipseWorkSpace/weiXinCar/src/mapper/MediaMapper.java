package com.neuedu.onlearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.onlearn.po.Media;

public interface MediaMapper {
	void insert(Media media);
	
	int getMediaByKeywordCount(@Param("type")int type,@Param("keyword")String keyword,@Param("teacherId")int teacherId);
	List<Media> getMediaByKeyword(@Param("begin")int begin,@Param("pageSize")int pageSize,@Param("type")int type,@Param("keyword")String keyword,@Param("teacherId")int teacherId);
}
