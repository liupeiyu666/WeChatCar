package com.newedu.onlearn.mapper;

import java.util.List;

import com.newedu.onlearn.po.CarUser;

public interface UsersMapper
{
   CarUser GetUserById(Integer p_id);
   //获取全部的数据
   List<CarUser> GetAllUsers();
   //添加用户
   void AddUser(CarUser p_CarUser);
}
