package com.ne.weixincar.onlearn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ne.weixincar.onlearn.pojo.CarSell;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/6/17
 * Time: 11:06
 * description:
 */
@Service
public interface CarSellService extends IService<CarSell> {

    List<CarSell> getAllSell();

    void addSell(CarSell carUser);

    void updateSell(CarSell carUser);
}
