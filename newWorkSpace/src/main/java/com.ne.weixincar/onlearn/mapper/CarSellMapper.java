package com.ne.weixincar.onlearn.mapper;

import java.util.List;

import com.ne.weixincar.onlearn.pojo.CarSell;

public interface CarSellMapper
{
	   //获取全部的数据
	   List<CarSell> getAllSell();
	   //添加用户
	   void addSell(CarSell p_CarUser);
	   //更新
	   void updateSell(CarSell p_CarUser);
}
