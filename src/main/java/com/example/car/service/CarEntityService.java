package com.example.car.service;

import com.example.car.model.CarEntity;

import java.util.List;

public interface CarEntityService {


    void create(CarEntity carEntity);

    void delete(Long id);

    void update(CarEntity carEntity);

    List<CarEntity> list(CarEntity carEntity, Integer page, Integer pageSize);

}
