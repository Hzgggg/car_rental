package com.example.car.service.impl;

import com.example.car.mapper.UserEntityMapper;
import com.example.car.model.UserEntity;
import com.example.car.service.UserEntityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Resource
    private UserEntityMapper userEntityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserEntity record) {
        return userEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        return userEntityMapper.insertSelective(record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Long id) {
        return userEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        return userEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        return userEntityMapper.updateByPrimaryKey(record);
    }

}
