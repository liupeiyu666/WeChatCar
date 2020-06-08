package com.neuedu.onlearn.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.neuedu.onlearn.util.D;
@WebListener
public class ReqListen implements ServletRequestListener{

	public void requestDestroyed(ServletRequestEvent sre) {
			D.closeConn();
		
		
	}

	public void requestInitialized(ServletRequestEvent arg0) {
		
	}

}
