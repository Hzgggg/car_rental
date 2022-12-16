package com.example.car.service.impl;

import com.example.car.mapper.CarEntityMapper;
import com.example.car.model.CarEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarEntityServiceImplTest {

    @Mock
    private CarEntityMapper carEntityMapper;

    @InjectMocks
    private CarEntityServiceImpl carEntityService;

    @Test
    void list() {
        ArrayList<CarEntity> carEntities = new ArrayList<>();
        CarEntity carEntity = new CarEntity();
        carEntity.setId(1L);
        carEntities.add(carEntity);

        Mockito.when(carEntityMapper.selectCarList(carEntity)).thenReturn(carEntities);

        List<CarEntity> result = carEntityService.list(carEntity, 1, 1);

        Assertions.assertEquals(result, carEntities);
    }
}