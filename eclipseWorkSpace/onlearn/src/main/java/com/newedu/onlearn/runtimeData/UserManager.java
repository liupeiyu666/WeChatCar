package com.newedu.onlearn.runtimeData;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;
/**
 由于频繁的访问数据库会造成性能消耗，所以这里进行临时数据的存储
 **/
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.newedu.onlearn.mapper.UsersMapper;
import com.newedu.onlearn.po.CarUser;
import com.newedu.onlearn.util.D;
public class UserManager
{
	private static UserManager _instance;

	public static UserManager get_instance()
	{
		if (_instance==null)
		{
			_instance=new UserManager();
		}
		return _instance;
	}
	private UserManager()
	{
		
	}
	Map m_map = new HashMap();
	/*
	 * 初始化加载所有的数据，之后运行时直接从这里获取，不需要每次请求都去数据库申请*/
	public void Init()
	{
		//初始化时加载所有数据
		SqlSession t_SqlSession=D.getConn();
		UsersMapper t_Mapper=D.getConn().getMapper(UsersMapper.class);
		List<CarUser> t_userList= t_Mapper.GetAllUsers();
		D.closeConn();
//		t_SqlSession.commit();
//		t_SqlSession.close();
		for (int i = 0; i < t_userList.size(); i++)
		{
			System.out.println("------------------:"+t_userList.get(i).WxOpenId);
			if (!m_map.containsKey(t_userList.get(i).WxOpenId))
			{
				m_map.put(t_userList.get(i).WxOpenId, t_userList.get(i));				
			}
		}
	}
	//添加用户
	public void AddUser(CarUser p_User)
	{
		if (!m_map.containsKey(p_User.WxOpenId))
		{
			m_map.put(p_User.WxOpenId, p_User);
			SqlSession t_SqlSession=D.getConn();
			UsersMapper t_Mapper=D.getConn().getMapper(UsersMapper.class);
			//数据库数据
			t_Mapper.AddUser(p_User);
			D.closeConn();
		}
	}
	//通过ID创建用户
	public void AddUserById(String p_opendId)
	{
		CarUser t_User=GetUser(p_opendId);
		if (t_User==null)
		{
			CarUser t_tempUser=new CarUser();
			t_tempUser.WxOpenId=p_opendId;
			AddUser(t_tempUser);
			System.out.println("添加用户成功---------："+p_opendId);
		}
	}
	//获取用户
	public CarUser GetUser(String p_opendId)
	{
		if (m_map.containsKey(p_opendId))
		{
			return (CarUser)m_map.get(p_opendId);
		}
		return null;
	}
	//更新用户信息
	public void UpdateUser(CarUser p_User)
	{
		if (m_map.containsKey(p_User.WxOpenId))
		{
			m_map.put(p_User.WxOpenId, p_User);
			SqlSession t_SqlSession=D.getConn();
			UsersMapper t_Mapper=D.getConn().getMapper(UsersMapper.class);
			//数据库数据
			//t_Mapper.(p_User);
			D.closeConn();
		}
		
	}
	public  boolean CheckVip(String p_openId)
	{
		CarUser t_CarUser= GetUser(p_openId);
		if (t_CarUser!=null)
		{
			if (t_CarUser.VipLevel>=0)
			{
				return true;
			}
		}
		return false;
	}
}
