package com.newedu.onlearn.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/app/*")
public class WebDispatcher extends HttpServlet
{
	public static final String PROJECT_NAME = "/onlearn/app/";
	private static final String PACKAGE_NAME = "com.newedu.onlearn.controller";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("doGet有这里");
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("doPost有这里");
		req.setCharacterEncoding("utf-8");
		//允许跨域访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		//获取用户的请求地址
		String uri = req.getRequestURI();
		Enumeration<String> paramKeys =req.getParameterNames();
		while(paramKeys.hasMoreElements()) {
			String key = paramKeys.nextElement();
			System.out.println("请求参数" + key +":" + req.getParameter(key));
		}
		//去掉路径中的项目名称
		uri = uri.replace(PROJECT_NAME, "");
		String[] reqUri = uri.split("/");
		String catName = PACKAGE_NAME + "." +reqUri[0];
		try
		{
			//获取指定类的类对象
			Class<?> clx = Class.forName(catName);
			//创建对象实例
			Object instance = clx.newInstance();
			//获取类调用的方法
			Method method = clx.getMethod(reqUri[1], HttpServletRequest.class,HttpServletResponse.class);
			Object obj = method.invoke(instance, req,resp);
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
