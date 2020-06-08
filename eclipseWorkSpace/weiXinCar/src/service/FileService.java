package com.neuedu.onlearn.service;

import java.io.IOException;

import javax.servlet.http.Part;

import com.neuedu.onlearn.po.Media;
import com.neuedu.onlearn.web.Page;

public interface FileService {
	/**
	 * 上传文件
	 * @param path
	 * @param part
	 * @param media
	 * @return
	 * @throws IOException
	 */
	String uploadFile(String path,Part part,Media media) throws IOException;
	/**
	 * 获取文件列表
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 * @param keyword
	 * @return
	 */
	Page<Media> list(int pageNum,int pageSize,int type,String keyword,int teacherId);
}
