package com.neuedu.onlearn.privilege;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.neuedu.onlearn.exception.NoPrivilegeException;
import com.neuedu.onlearn.po.Teacher;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.util.SessionManager;
import com.neuedu.onlearn.web.MyFilter;
import com.neuedu.onlearn.web.MyFilterChain;


/**
 * 用来验证用户是否登陆
 * @author 11961
 *
 */
public class LoginFilter implements MyFilter{
	private static Logger log = LogManager.getLogger(Logger.class);
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		
		log.info("登陆验证");
		//获取token
		String token = req.getParameter("token");
		if(token == null) {
			Cookie[] cookies = req.getCookies();
			if(cookies !=null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("JSESSIONID")) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}
		log.info("获取token：" + token);
		//验证token合法性
		if(token == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
			
		}
		HttpSession session = SessionManager.getSession(token);
		
		if(session == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
		}
		//验证session是否过期
		long lastAccessTime = session.getLastAccessedTime();
		long curTime = System.currentTimeMillis();
		if((curTime - lastAccessTime) >= session.getMaxInactiveInterval()*1000) {
			SessionManager.remove(token);
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"登陆过期，请重新登陆");
		}
		Teacher teacher = (Teacher) session.getAttribute("userInfo");
		if(teacher == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
		}
		//存储token
		req.setAttribute("token", token);
		
		String role = teacher.getRole();
		if(Global.ROLE_ADMIN.equals(role)) {
			log.info("管理员权限，不需要验证");
			return;
		}
		
		
		 
		//调用后继过滤器
		chain.doFilter(req, resp);
	}

}
