package com.neuedu.onlearn.privilege;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.web.MyFilter;
import com.neuedu.onlearn.web.MyFilterChain;
import com.neuedu.onlearn.web.WebDispatcher;
/**
 * 白名单验证
 * @author 11961
 *
 */
public class WhiteFilter implements MyFilter {
	private static final List<String> whiteList;
	private Logger log = LogManager.getLogger(WhiteFilter.class);
	static {
		whiteList = new ArrayList<String>();
		whiteList.add("teacher/login");
	}
	

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		String path = (String)req.getAttribute(Global.REQ_PATH);
		if(!whiteList.contains(path)) {
			chain.doFilter(req, resp);
		}else {
			log.info("通过白名单验证");
		}
		
	}

}
