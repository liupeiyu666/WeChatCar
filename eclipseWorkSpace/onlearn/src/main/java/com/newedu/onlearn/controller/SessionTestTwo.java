package com.newedu.onlearn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionTestTwo 
{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setCharacterEncoding("UTF=8");
		resp.setContentType("text/html;charset=UTF-8");
		System.out.println("===============");
		HttpSession t_Session = req.getSession();
		if (t_Session.isNew())
		{
			resp.getWriter().write("我是新创建的" + t_Session.getId());
			t_Session.setAttribute("MM", "玉玉好好");
		} else
		{
			resp.getWriter().write("已经有我了：" + t_Session.getId());
		}

	}
	public void name(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.getWriter().write("--------已经有我了：" );
	}
}
