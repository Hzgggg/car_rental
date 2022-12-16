package com.example.car.service.impl;

import com.example.car.mapper.CarEntityMapper;
import com.example.car.model.CarEntity;
import com.example.car.service.CarEntityService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarEntityServiceImpl implements CarEntityService {

    @Resource
    private CarEntityMapper carEntityMapper;


    @Override
    public void create(CarEntity carEntity) {
        synchronized (CarEntityServiceImpl.class) {
            carEntityMapper.insertSelective(carEntity);
        }
    }

    @Override
    public void delete(Long id) {
        synchronized (CarEntityServiceImpl.class) {
            CarEntity carEntity = new CarEntity();
            carEntity.setId(id);
            carEntity.setDeleteFlag(1);
            carEntityMapper.updateByPrimaryKeySelective(carEntity);
        }
    }

    @Override
    public void update(CarEntity carEntity) {
        synchronized (CarEntityServiceImpl.class) {
            carEntityMapper.updateByPrimaryKeySelective(carEntity);
        }
    }

    @Override
    public List<CarEntity> list(CarEntity carEntity, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return carEntityMapper.selectCarList(carEntity);
    }
}
