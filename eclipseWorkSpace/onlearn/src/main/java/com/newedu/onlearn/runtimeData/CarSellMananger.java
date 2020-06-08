package com.newedu.onlearn.runtimeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.newedu.onlearn.mapper.CarSellMapper;
import com.newedu.onlearn.mapper.UsersMapper;
import com.newedu.onlearn.po.CarSell;
import com.newedu.onlearn.po.CarUser;
import com.newedu.onlearn.util.D;

public class CarSellMananger
{
	private static CarSellMananger _instance;

	public static CarSellMananger get_instance()
	{
		if (_instance==null)
		{
			_instance=new CarSellMananger();
		}
		return _instance;
	}
	private CarSellMananger()
	{
		
	}
	/*
	 * Key为wxopenID，value为list存储元素为carsell*/
	Map<String, List<OneCarSell>> m_map=new HashMap<String,List<OneCarSell>>();
	/*根据时间排序*/
	List<OneCarSell> m_timeSortList=new LinkedList<OneCarSell>();
	
	/*
	 * 初始化加载所有的数据，之后运行时直接从这里获取，不需要每次请求都去数据库申请*/
	public void Init()
	{
		//初始化时加载所有数据
		SqlSession t_SqlSession=D.getConn();
		CarSellMapper t_Mapper=D.getConn().getMapper(CarSellMapper.class);
		List<CarSell> t_sellList= t_Mapper.GetAllSell();
		D.closeConn();
//		t_SqlSession.commit();
//		t_SqlSession.close();
		for (int i = 0; i < t_sellList.size(); i++)
		{
			CarSell t_item=t_sellList.get(i);
			OneCarSell t_OneCarSell=new OneCarSell(t_item);
			if (!m_map.containsKey(t_item.WxOpenId))
			{
				List<OneCarSell> t_carsellList=new ArrayList<OneCarSell>();
				t_carsellList.add(t_OneCarSell);
				m_map.put(t_sellList.get(i).WxOpenId, t_carsellList);				
			}
			else {
				m_map.get(t_item.WxOpenId).add(t_OneCarSell);
			}
			//设置各个排序容器
			m_timeSortList.add(t_OneCarSell);
		}
		
	}
	//添加用户
	public void AddSell(OneCarSell p_OneCarSell)
	{
		if (!m_map.containsKey(p_OneCarSell.WxOpenId))
		{
			List<OneCarSell> t_carsellList=new ArrayList<OneCarSell>();
			t_carsellList.add(p_OneCarSell);
			m_map.put(p_OneCarSell.WxOpenId, t_carsellList);
		}
		else {
			m_map.get(p_OneCarSell.WxOpenId).add(p_OneCarSell);
		}
		//设置各个排序容器
		m_timeSortList.add(p_OneCarSell);
		//数据库数据
		SqlSession t_SqlSession=D.getConn();
		CarSellMapper t_Mapper=D.getConn().getMapper(CarSellMapper.class);		
		t_Mapper.AddSell(p_OneCarSell.ToCarSell());
		t_SqlSession.commit();
		t_SqlSession.close();
	}
	/*
	 * 更新出售信息*/
	public void UpdateSell(OneCarSell p_OneCarSell)
	{
		//直接更新数据库即可，可以在控制层直接修改
		SqlSession t_SqlSession=D.getConn();
		CarSellMapper t_Mapper=D.getConn().getMapper(CarSellMapper.class);		
		t_Mapper.UpdateSell(p_OneCarSell.ToCarSell());
		t_SqlSession.commit();
		t_SqlSession.close();
	}
	/*
	 * 根据openid获取这个用户的所有的出售信息*/
	public List<OneCarSell> GetUsersByOpenId(String p_opendId)
	{
		if (m_map.containsKey(p_opendId))
		{
			return m_map.get(p_opendId);
		}
		return null;
	}
	/*发布一次信息     */
	synchronized public void AddPublishInfo(CarSell p_CarSell)
	{
		
		//System.out.println("添加发布信息："+p_CarSell.WxOpenId+"      "+m_map.size());
		//1.检测是否有openid的信息
		List<OneCarSell> t_tempList=GetUsersByOpenId(p_CarSell.WxOpenId);
		if (t_tempList==null)
		{
			//添加一个
			AddSell(new OneCarSell(p_CarSell));
		//	System.out.println("没有。新添加一个");
		}
		else {
			//1.找找有没有本次发布的信息
			OneCarSell t_temp=null;
			for (int i = 0; i < t_tempList.size(); i++)
			{
				if (t_tempList.get(i).Date.equals(p_CarSell.Date))
				{
					t_temp=t_tempList.get(i);
				}
			}
			if (t_temp!=null)
			{
				//合并图片信息
				t_temp.SourceUrlList.add(p_CarSell.SourceUrl);		
		//		System.out.println("合并信息====");
				//更新
				UpdateSell(t_temp);
			}
			else {
		//		System.out.println("时间不一致，重新添加一次");
				//添加一个
				AddSell(new OneCarSell(p_CarSell));
			}
		}
	}
	/*
	 * 获取一个默认的排序，就是按照时间排序*/
	public List<OneCarSell> GetUsersByDefault()
	{
		return m_timeSortList;
	}

	/////////////////////////////排序功能///////////////////////////////////////////////////////
	private void SortByTime()
	{
		 Collections.sort(m_timeSortList, new Comparator<OneCarSell>() {
	            public int compare(OneCarSell h1, OneCarSell h2) {
	                return 1;
	            }
	        });
	}
}
