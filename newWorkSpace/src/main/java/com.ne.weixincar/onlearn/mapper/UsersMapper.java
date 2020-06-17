package com.ne.weixincar.onlearn.mapper;

import java.util.List;

import com.ne.weixincar.onlearn.pojo.CarUser;

public interface UsersMapper
{
   CarUser getUserById(Integer p_id);
   //获取全部的数据
   List<CarUser> getAllUsers();
   //添加用户
   void addUser(CarUser p_CarUser);
}
