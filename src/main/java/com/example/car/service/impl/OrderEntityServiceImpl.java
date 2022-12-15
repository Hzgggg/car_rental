package com.example.car.service.impl;

import com.example.car.mapper.OrderEntityMapper;
import com.example.car.model.OrderEntity;
import com.example.car.service.OrderEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    @Resource
    private OrderEntityMapper orderEntityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderEntity record) {
        return orderEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderEntity record) {
        return orderEntityMapper.insertSelective(record);
    }

    @Override
    public OrderEntity selectByPrimaryKey(Long id) {
        return orderEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderEntity record) {
        return orderEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderEntity record) {
        return orderEntityMapper.updateByPrimaryKey(record);
    }

}
