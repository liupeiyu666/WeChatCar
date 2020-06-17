package com.ne.weixincar.onlearn.service.Imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ne.weixincar.onlearn.mapper.CarSellMapper;
import com.ne.weixincar.onlearn.pojo.CarSell;
import com.ne.weixincar.onlearn.service.CarSellService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: hataki
 * @Date: 2020/6/17
 * Time: 13:44
 * description:
 */
public class CarSellServiceImp  implements CarSellService {

    @Autowired
    private CarSellMapper carSellMapper ;

    @Override
    public List<CarSell> getAllSell(){
        return carSellMapper.getAllSell();
    };

    @Override
    public void addSell(CarSell carUser){
        carSellMapper.addSell(carUser);
    };

    @Override
    public void updateSell(CarSell carUser){
        carSellMapper.updateSell(carUser);
    };

    @Override
    public boolean saveBatch(Collection<CarSell> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<CarSell> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<CarSell> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(CarSell entity) {
        return false;
    }

    @Override
    public CarSell getOne(Wrapper<CarSell> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<CarSell> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<CarSell> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<CarSell> getBaseMapper() {
        return null;
    }


}
