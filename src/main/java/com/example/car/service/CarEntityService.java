package com.example.car.service;

import com.example.car.model.CarEntity;

import java.util.List;

public interface CarEntityService {


    int deleteByPrimaryKey(Long id);

    int insert(CarEntity record);

    int insertSelective(CarEntity record);

    CarEntity selectByPrimaryKey(Long id);

    List<CarEntity> selectCarList(CarEntity carEntity);

    int updateByPrimaryKeySelective(CarEntity record);

    int updateByPrimaryKey(CarEntity record);

}
