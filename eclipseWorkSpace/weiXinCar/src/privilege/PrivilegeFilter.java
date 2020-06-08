package com.neuedu.onlearn.privilege;

import java.util.ArrayList;
import java.util.List;

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
 * 权限验证
 * @author 11961
 *
 */
public class PrivilegeFilter implements MyFilter{
	private static List<String> teachers;
	private Logger log = LogManager.getLogger(PrivilegeFilter.class);
	public PrivilegeFilter() {
		this.teachers = new ArrayList<String>();
		teachers.add("teacher/add");
		teachers.add("teacher/list");
		teachers.add("teacher/update");
		teachers.add("teacher/find");
		teachers.add("teacher/delete");
		teachers.add("course/add");
		teachers.add("course/list");
		teachers.add("file/upload");
		teachers.add("file/list");
		
	}
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		String reqPath = (String) req.getAttribute(Global.REQ_PATH);
		String sid = (String) req.getAttribute("token");
		HttpSession session = SessionManager.getSession(sid);
		Teacher teacher = (Teacher) session.getAttribute("userInfo");
		String role = teacher.getRole();
		
		if(!Global.ROLE_TEACHER.equals(role)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE,E.NO_PRIVILEGE_ERROR_INFO);
		}
		if(!teachers.contains(reqPath)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE,E.NO_PRIVILEGE_ERROR_INFO);
		}
		log.info("通过权限验证");
		chain.doFilter(req, resp);
	}
	
}
