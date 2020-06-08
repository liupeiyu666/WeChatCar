package com.neuedu.onlearn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyFilter {
	public void doFilter(HttpServletRequest req,HttpServletResponse resp,MyFilterChain chain);

}
