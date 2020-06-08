package com.newedu.onlearn.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.newedu.onlearn.runtimeData.CarSellMananger;
import com.newedu.onlearn.runtimeData.UserManager;

public class MainEnter implements ServletContextListener
{

	public void contextDestroyed(ServletContextEvent arg0)
	{
		// TODO Auto-generated method stub
		System.out.println("+++++++++contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		System.out.println("+++contextInitialized-------");
//		System.out.println(UserManager.get_instance());
//		//1.初始化数据库所有数据
		UserManager.get_instance().Init();
		//2.初始化出售的数据
		CarSellMananger.get_instance().Init();
	}

}
