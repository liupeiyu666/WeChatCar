package com.ne.weixincar.onlearn.controller;

import org.springframework.stereotype.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class TestInfo
{
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		
		 System.out.println( new String(request.getParameter("operFlag").getBytes("ISO-8859-1"), "UTF-8"));
		 response.getWriter().write("我也是中文");
	 }
}