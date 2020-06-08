package com.newedu.onlearn.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.newedu.onlearn.util.D;

@WebListener
public class Reqlistener implements ServletRequestListener
{

	public void requestDestroyed(ServletRequestEvent arg0)
	{
		System.out.println("--------销毁--------");
		//D.closeConn();
	}

	public void requestInitialized(ServletRequestEvent arg0)
	{
		// TODO Auto-generated method stub
		HttpServletRequest t_HttpServlet=(HttpServletRequest)arg0.getServletRequest();
		System.out.println("********创建了："+t_HttpServlet.getRequestURI());
	}
	
}
