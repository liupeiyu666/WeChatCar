package com.newedu.onlearn.mapper;

import java.util.List;

import com.newedu.onlearn.po.CarSell;

public interface CarSellMapper
{
	   //获取全部的数据
	   List<CarSell> GetAllSell();
	   //添加用户
	   void AddSell(CarSell p_CarUser);
	   //更新
	   void UpdateSell(CarSell p_CarUser);
}
