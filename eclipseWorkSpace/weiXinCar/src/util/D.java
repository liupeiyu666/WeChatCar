package com.neuedu.onlearn.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class D {
	private static SqlSessionFactory ssf;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private static Logger log = LogManager.getLogger(D.class);
	static {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("创建连接失败");
		}
	}
	/**
	 * 建立数据库链接
	 * @return
	 * @throws IOException 
	 */
	public static SqlSession getConn(){
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession == null) {
			sqlSession = ssf.openSession();
			threadLocal.set(sqlSession);
		}
		log.info("创建数据库链接" + sqlSession);
		return sqlSession;
	}
	/**
	 * 关闭数据库链接
	 * @param args
	 * @throws IOException
	 */
	public static void closeConn() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession!=null) {
			log.info("关闭数据库链接" + sqlSession);
			sqlSession.commit();
			sqlSession.close();
			threadLocal.remove();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		System.out.println(D.getConn());
	}

}
