package com.neuedu.onlearn.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.onlearn.exception.BaseException;
import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.privilege.LoginFilter;
import com.neuedu.onlearn.privilege.PrivilegeFilter;
import com.neuedu.onlearn.privilege.WhiteFilter;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.util.NameUtil;

/**
 * 程序分发器，程序的统一入口
 * @author 11961
 *
 */
@WebServlet("/app/*")
@MultipartConfig
public class WebDispatcher extends HttpServlet{
	public static final String PROJECT_NAME = "/onlearn/app/";
	private static final String PACKAGE_NAME = "com.neuedu.onlearn.controller";
	private static ObjectMapper objMapper = new ObjectMapper();
	private static Logger log = LogManager.getLogger(WebDispatcher.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//允许跨域访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		//获取用户的请求地址
		String uri = req.getRequestURI();
		
		//记录用户请求参数和请求路径
		log.info(req.getRemoteHost() + "请求地址：" + uri);
		Enumeration<String> paramKeys =req.getParameterNames();
		while(paramKeys.hasMoreElements()) {
			String key = paramKeys.nextElement();
			log.info("请求参数" + key +":" + req.getParameter(key));
		}
		//去掉路径中的项目名称
		uri = uri.replace(PROJECT_NAME, "");
		//将请求对象存储到reqest对象中
		req.setAttribute(Global.REQ_PATH, uri);
		//权限验证
		MyFilterChain chain = new MyFilterChain();
		//白名单验证器
		MyFilter whileFilter =new WhiteFilter();
		//用户登陆验证
		MyFilter loginFilter = new LoginFilter();
		//权限验证
		MyFilter privilegeFilter = new PrivilegeFilter();
		
		chain.addFilter(whileFilter).addFilter(loginFilter).addFilter(privilegeFilter);
		ApiResult apiResult = new ApiResult();
		try {
			chain.doFilter(req, resp);
			//如果是多媒体类型，设置上传目录的路径
			if(req.getHeader("content-type") != null && req.getHeader("content-type").startsWith("multipart")) {
				String uploadDir = this.getServletContext().getRealPath("/");
				//req.setAttribute("upload_path", this.getServletContext().getRealPath("/"));
				log.info("设置文件上传路径："+ uploadDir);
				req.setAttribute("upload_path", uploadDir);
			}
			//使用/分割字符串，结果为数组
			String[] reqUri = uri.split("/");
			if(reqUri.length < 2) {
				throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_INFO);
			}
			String cat = reqUri[0];
			String opt = reqUri[1];
			cat = NameUtil.convert2Caml(cat);
			cat = NameUtil.firstUpper(cat);
			opt = NameUtil.convert2Caml(opt);
			
			String catName = PACKAGE_NAME + "." +cat + "Controller";
			//获取指定类的类对象
			Class<?> clx = Class.forName(catName);
			//创建对象实例
			Object instance = clx.newInstance();
			//获取类调用的方法
			Method method = clx.getMethod(opt, HttpServletRequest.class,HttpServletResponse.class);
			Object obj = method.invoke(instance, req,resp);
			if(obj != null) {
				apiResult.setData(obj);
			}
		}catch(InvocationTargetException e){
			e.printStackTrace();
			//获取引起异常的额根本异常
			Throwable targetE = e.getTargetException();
			if(targetE instanceof BaseException) {
				BaseException srcException = (BaseException)targetE;
				apiResult.setCode(srcException.getCode());
				apiResult.setMsg(srcException.getMessage());
			}else{
				e.printStackTrace();
				apiResult.setCode(500);
				apiResult.setMsg("程序内部错误");
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			apiResult.setCode(E.PATH_ERROR_CODE);
			apiResult.setMsg(E.PATH_ERROR_INFO);
		}catch(BaseException e){
			apiResult.setCode(e.getCode());
			apiResult.setMsg(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			apiResult.setCode(500);
			apiResult.setMsg("程序内部错误");
		} 
		String rsStr = objMapper.writeValueAsString(apiResult);
		
		log.info("返回用户数据：" + rsStr);
		resp.getWriter().write(rsStr);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
