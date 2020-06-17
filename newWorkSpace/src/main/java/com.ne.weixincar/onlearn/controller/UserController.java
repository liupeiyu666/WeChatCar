package com.ne.weixincar.onlearn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.Region;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sss/test")
public class UserController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");


		HttpSession t_Session = req.getSession();

		System.out.println(req.getRequestURL());
		if (t_Session.isNew())
		{
			resp.getWriter().write("我是新创建的" + t_Session.getId());
			t_Session.setAttribute("MM", "玉玉好好");
		} else
		{
			resp.getWriter().write("已经有我了：" + t_Session.getId());
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("收到上傳----");
		String t_path="D:/JavaImage/";
		// 设置图片上传
		File dir = new File(t_path);
		if (!dir.exists())
		{
			dir.mkdir();
		}
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话,上传大的文件会占用很多内存，
		// 设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
		/**
		 * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上， 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
		 * 然后再将其真正写到对应目录的硬盘上
		 */
		factory.setRepository(dir);
		// 设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list;
		try
		{
			
			list = upload.parseRequest((RequestContext) req);
			FileItem picture = null;
			for (FileItem item : list)
			{
				// 获取表单的属性名字
				String name = item.getFieldName();
				// 如果获取的表单信息是普通的 文本 信息
				if (item.isFormField())
				{
					// 获取用户具体输入的字符串
					String value = item.getString();
					System.out.println("name=" + name + ",value=" + value);
				} else
				{
					System.out.println("这是一张图片啊啊啊啊啊："+name);
					picture = item;
					//自定义上传图片的名字为userId.jpg
		            String fileName = UUID.randomUUID().toString();
		            String destPath = t_path + fileName+".jpg";
		            //真正写到磁盘上
		            File file = new File(destPath);
		            OutputStream out = new FileOutputStream(file);
		            InputStream in = picture.getInputStream();
		            int length = 0;
		            byte[] buf = new byte[1024];
		            // in.read(buf) 每次读到的数据存放在buf 数组中
		            while ((length = in.read(buf)) != -1) {
		                //在buf数组中取出数据写到（输出流）磁盘上
		                out.write(buf, 0, length);
		            }
		            in.close();
		            out.close();
		            System.out.println("书写完毕了------------：");
				}
			}
		} catch (FileUploadException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
