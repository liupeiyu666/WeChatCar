package com.ne.weixincar.onlearn.service;

import com.ne.weixincar.onlearn.pojo.CarUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/6/17
 * Time: 11:06
 * description:
 */
@Service
public interface CarUserService {

    List<CarUser> getAllUser();

    void addUser(CarUser carUser);
}
