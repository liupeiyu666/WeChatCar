package com.newedu.onlearn.controller;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.fabric.xmlrpc.base.Data;
import com.newedu.onlearn.po.CarSell;
import com.newedu.onlearn.runtimeData.CarSellMananger;
import com.newedu.onlearn.runtimeData.OneCarSell;
import com.newedu.onlearn.runtimeData.UserManager;
import com.newedu.onlearn.util.ErrorInfo;
import com.newedu.onlearn.util.GameUtils;

public class PublishController
{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
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
			list = upload.parseRequest(req);
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
					String value =GameUtils.GetUTF(item.getString()) ;
					System.out.println("name=" + name+"    "+value);
					//检测是否是会员
					if (name.equals("openid"))
					{
						if (!UserManager.get_instance().CheckVip(value))
						{
							resp.getWriter().write(ErrorInfo.NotVip);
							return;
						}
						t_OneCarSell.WxOpenId=value;
					}
					//检测是否超出了发布上限
					//记录时间 timeRecord
					if (name.equals("timeRecord"))
					{
						t_OneCarSell.Date=value;
					}
					else if (name.equals("latitude")&&value!=null) {
						t_OneCarSell.Latitude=Double.parseDouble(value);				
						
					}
					else if (name.equals("longitude")&&value!=null) {
						t_OneCarSell.Longitude=Double.parseDouble(value);
					}
					else if (name.equals("locationName")) {
						t_OneCarSell.Location=value;
					}
					else if (name.equals("notes")) {
						t_OneCarSell.Title=value;
					}
					else if (name.equals("price")&&value!=null) {
						t_OneCarSell.Price=Integer.parseInt(value);
					}
					else if (name.equals("namePicker")) {
						t_OneCarSell.GoodsName=value;
					}
					else if (name.equals("nickName")) {
						t_OneCarSell.NickName=value;
					}
					else if (name.equals("avatarurl")) {
						t_OneCarSell.AvatarUrl=value;
					}
				} else
				{
					picture = item;
					// 自定义上传图片的名字为userId.jpg
					String fileName = UUID.randomUUID().toString();
					String destPath = t_path + fileName + ".jpg";
					t_OneCarSell.SourceUrl=t_folder+ fileName + ".jpg";
					System.out.println("======图片资源地址："+t_OneCarSell.SourceUrl);
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
