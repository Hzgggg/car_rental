package com.example.car.service;

import com.example.car.model.OrderEntity;

public interface OrderEntityService {


    int deleteByPrimaryKey(Long id);

    int insert(OrderEntity record);

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderEntity record);

    int updateByPrimaryKey(OrderEntity record);

}
