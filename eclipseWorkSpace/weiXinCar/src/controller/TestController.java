package com.neuedu.onlearn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.SessionManager;

@WebServlet("/teacher/test")
public class TestController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		if(token == null || token.length() == 0) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_INFO);
		}
		HttpSession session = SessionManager.getSession(token);
		if(session == null) {
			throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_INFO);
		}
		Object teacher = session.getAttribute("userInfo");
		if(teacher == null) {
			throw new InvalidParamException(1003,"请登录。。");
		}
		resp.getWriter().write(teacher.toString());
	}
}
