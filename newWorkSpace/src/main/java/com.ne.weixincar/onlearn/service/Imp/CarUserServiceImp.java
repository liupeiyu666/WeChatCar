package com.ne.weixincar.onlearn.service.Imp;

import com.ne.weixincar.onlearn.mapper.UsersMapper;
import com.ne.weixincar.onlearn.pojo.CarUser;
import com.ne.weixincar.onlearn.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/6/17
 * Time: 11:07
 * description:
 */
public class CarUserServiceImp implements CarUserService {

    @Autowired
    private UsersMapper usersMapper ;

    @Override
    public List<CarUser> getAllUser(){
        return usersMapper.getAllUsers();
    };

    @Override
    public void addUser(CarUser carUser) {
         usersMapper.addUser(carUser);
    }
}
