package com.neuedu.onlearn.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neuedu.onlearn.util.V;
@WebServlet("/media/upload")
//处理文件上传
@MultipartConfig
public class UploadFileServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] params = {"desc","file_type"};
		V.valid(req, params);
		Part part = req.getPart("fail");
		String type = part.getContentType();
		long size = part.getSize();
		String name = part.getName();
		String submitName = part.getSubmittedFileName();
		String dirPath = "/upload_file/images";
		String baseDir = this.getServletContext().getRealPath(dirPath);
		File file = new File(baseDir);
		//如过文件夹不存在，则创建文件夹
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = Long.toHexString(System.currentTimeMillis());
		if(type.endsWith("jpeg")) {
			fileName = fileName + ".jpg";
			
		}else {
			return;
		}
		baseDir += fileName;
		part.write(baseDir);
		System.out.println(dirPath + fileName);
	}
}
