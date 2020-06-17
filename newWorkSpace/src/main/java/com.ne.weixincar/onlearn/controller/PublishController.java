package com.ne.weixincar.onlearn.controller;

import com.ne.weixincar.onlearn.pojo.CarSell;
import com.ne.weixincar.onlearn.runtimeData.CarSellMananger;
import com.ne.weixincar.onlearn.runtimeData.UserManager;
import com.ne.weixincar.onlearn.util.ErrorInfo;
import com.ne.weixincar.onlearn.util.GameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController
{

	@RequestMapping("public/v1/home/swiperdata")
	public void swiperdata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String contexPath = req.getSession().getServletContext().getRealPath("/");
        String t_folder="Images/";
		String t_path = contexPath + t_folder;
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
			CarSell t_OneCarSell=new CarSell();
			for (FileItem item : list)
			{
				// 获取表单的属性名字
				String name = item.getFieldName();
				
				// 如果获取的表单信息是普通的 文本 信息
				if (item.isFormField())
				{
					// 获取用户具体输入的字符串
					String value = GameUtils.GetUTF(item.getString()) ;
					System.out.println("name=" + name+"    "+value);
					//检测是否是会员
					if (name.equals("openid"))
					{
						if (!UserManager.get_instance().CheckVip(value))
						{
							resp.getWriter().write(ErrorInfo.NotVip);
							return;
						}
						t_OneCarSell.setWxOpenId(value);
					}
					//检测是否超出了发布上限
					//记录时间 timeRecord
					if (name.equals("timeRecord"))
					{
						t_OneCarSell.setDate(value);
					}
					else if (name.equals("latitude")&&value!=null) {
						t_OneCarSell.setLatitude(Double.parseDouble(value));
						
					}
					else if (name.equals("longitude")&&value!=null) {
						t_OneCarSell.setLongitude(Double.parseDouble(value));
					}
					else if (name.equals("locationName")) {
						t_OneCarSell.setLocation(value);
					}
					else if (name.equals("notes")) {
						t_OneCarSell.setTitle(value);
					}
					else if (name.equals("price")&&value!=null) {
						t_OneCarSell.setPrice(Integer.parseInt(value));
					}
					else if (name.equals("namePicker")) {
						t_OneCarSell.setGoodsName(value);
					}
					else if (name.equals("nickName")) {
						t_OneCarSell.setNickName(value);
					}
					else if (name.equals("avatarurl")) {
						t_OneCarSell.setAvatarUrl(value);
					}
				} else
				{
					picture = item;
					// 自定义上传图片的名字为userId.jpg
					String fileName = UUID.randomUUID().toString();
					String destPath = t_path + fileName + ".jpg";
					t_OneCarSell.setSourceUrl(t_folder+ fileName + ".jpg");
					System.out.println("======图片资源地址："+t_OneCarSell.getSourceUrl());
					// 真正写到磁盘上
					File file = new File(destPath);
					OutputStream out = new FileOutputStream(file);
					InputStream in = picture.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					// in.read(buf) 每次读到的数据存放在buf 数组中
					while ((length = in.read(buf)) != -1)
					{
						// 在buf数组中取出数据写到（输出流）磁盘上
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
				}
				
			}
			//添加信息--能到这里说明已经注册了会员
			CarSellMananger.get_instance().AddPublishInfo(t_OneCarSell);
		} catch (FileUploadException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
