package com.example.car.service.impl;

import com.example.car.mapper.CarEntityMapper;
import com.example.car.model.CarEntity;
import com.example.car.service.CarEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarEntityServiceImpl implements CarEntityService {

    @Resource
    private CarEntityMapper carEntityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return carEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarEntity record) {
        return carEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(CarEntity record) {
        return carEntityMapper.insertSelective(record);
    }

    @Override
    public CarEntity selectByPrimaryKey(Long id) {
        return carEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarEntity> selectCarList(CarEntity carEntity) {
        return carEntityMapper.selectCarList(carEntity);
    }

    @Override
    public int updateByPrimaryKeySelective(CarEntity record) {
        return carEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarEntity record) {
        return carEntityMapper.updateByPrimaryKey(record);
    }

}
