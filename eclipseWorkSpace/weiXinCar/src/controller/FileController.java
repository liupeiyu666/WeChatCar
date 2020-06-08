package com.neuedu.onlearn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.po.Media;
import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.service.FileService;
import com.neuedu.onlearn.service.FileServiceImpl;

import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.V;
import com.neuedu.onlearn.web.Page;

public class FileController {
	private FileService fileService;
	public FileController() {
		fileService = new FileServiceImpl();
	}
	/**
	 * 文件上传接口
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String upload(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		String[] params = {"file_desc"};
		V.valid(req, params);
		Media media = V.entity(req, Media.class, params);
		Teacher teacher = (Teacher)req.getSession().getAttribute("userInfo");
		media.setTeacher(teacher);
		Part part = req.getPart("file");
		if(part == null) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_INFO);
		}
		String path = (String) req.getAttribute("upload_path");
		String filePath = fileService.uploadFile(path, part, media);
		return filePath;
	}
	/**
	 * 按条件返回媒体信息
	 * @param req
	 * @param resp
	 * @return
	 */
	public Page<Media> list(HttpServletRequest req,HttpServletResponse resp){
		String typeStr = V.getValue(req, "type", "0");
		String keyword = V.getValue(req, "keyword", "");
		String pageSizeStr = V.getValue(req, "page_size", "10");
		String pageNumStr = V.getValue(req, "page_num", "1");
		
		int type = Integer.parseInt(typeStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		int pageNum = Integer.parseInt(pageNumStr);
		
		Teacher teacher = (Teacher)req.getSession().getAttribute("userInfo");
		
		
		Page<Media> datas =  fileService.list(pageNum, pageSize, type, keyword,teacher.getId());
	
		return datas;
		
	}
}
