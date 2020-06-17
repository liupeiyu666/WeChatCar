package com.ne.weixincar.onlearn.runtimeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ne.weixincar.onlearn.pojo.CarSell;
import com.ne.weixincar.onlearn.pojo.OneCarSell;
import com.ne.weixincar.onlearn.service.CarSellService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;


public class CarSellMananger
{

	@Autowired
	private CarSellService carSellService ;

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
		List<CarSell> t_sellList= carSellService.getAllSell();
		for (int i = 0; i < t_sellList.size(); i++)
		{
			CarSell t_item=t_sellList.get(i);
			OneCarSell t_OneCarSell=new OneCarSell(t_item);
			if (!m_map.containsKey(t_item.getWxOpenId()))
			{
				List<OneCarSell> t_carsellList=new ArrayList<OneCarSell>();
				t_carsellList.add(t_OneCarSell);
				m_map.put(t_sellList.get(i).getWxOpenId(), t_carsellList);
			}
			else {
				m_map.get(t_item.getWxOpenId()).add(t_OneCarSell);
			}
			//设置各个排序容器
			m_timeSortList.add(t_OneCarSell);
		}

	}
	//添加用户
	public void AddSell(OneCarSell oneCarSell)
	{
		if (!m_map.containsKey(oneCarSell.getWxOpenId()))
		{
			List<OneCarSell> t_carsellList=new ArrayList<OneCarSell>();
			t_carsellList.add(oneCarSell);
			m_map.put(oneCarSell.getWxOpenId(), t_carsellList);
		}
		else {
			m_map.get(oneCarSell.getWxOpenId()).add(oneCarSell);
		}
		//设置各个排序容器
		m_timeSortList.add(oneCarSell);

		carSellService.addSell(oneCarSell.ToCarSell());

	}
	/*
	 * 更新出售信息*/
	public void UpdateSell(OneCarSell oneCarSell)
	{
		//直接更新数据库即可，可以在控制层直接修改

		carSellService.updateSell(oneCarSell.ToCarSell());

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

		//System.out.println("添加发布信息："+p_CarSell.getWxOpenId()+"      "+m_map.size());
		//1.检测是否有openid的信息
		List<OneCarSell> t_tempList=GetUsersByOpenId(p_CarSell.getWxOpenId());
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
				if (t_tempList.get(i).Date.equals(p_CarSell.getDate()))
				{
					t_temp=t_tempList.get(i);
				}
			}
			if (t_temp!=null)
			{
				//合并图片信息
				t_temp.SourceUrlList.add(p_CarSell.getSourceUrl());
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
			@Override
			public int compare(OneCarSell h1, OneCarSell h2) {
				return 1;
			}
		});
	}
}
