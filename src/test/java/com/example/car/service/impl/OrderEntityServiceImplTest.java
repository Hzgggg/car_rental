package com.example.car.service.impl;

import com.example.car.exception.ServiceException;
import com.example.car.mapper.CarEntityMapper;
import com.example.car.mapper.OrderEntityMapper;
import com.example.car.mapper.UserEntityMapper;
import com.example.car.model.CarEntity;
import com.example.car.model.OrderEntity;
import com.example.car.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderEntityServiceImplTest {

    @Mock
    private OrderEntityMapper orderEntityMapper;

    @Mock
    private CarEntityMapper carEntityMapper;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private OrderEntityServiceImpl orderEntityService;

    @Test
    @DisplayName("Test list Order api")
    void listOrder() {

        ArrayList<OrderEntity> orderEntities = new ArrayList<>();
        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setId(1L);
        orderEntities.add(orderEntity1);

        Mockito.when(orderEntityMapper.selectUnCompleteKeySelective(orderEntity1)).thenReturn(orderEntities);

        List<OrderEntity> result = orderEntityService.listOrder(orderEntity1, 1, 1);

        Assertions.assertEquals(result, orderEntities);
    }

    @Test
    void createOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setUserId(1L);
        orderEntity.setOrderStatus(0);
        orderEntity.setCarId(1L);
        orderEntity.setRentalStartTime(new Date(2022, 12, 18));
        orderEntity.setRentalEndTime(new Date(2022, 12, 20));
        orderEntity.setCarId(1L);
        Double total = Double.valueOf(200 * 2);

        CarEntity carEntity = new CarEntity();
        carEntity.setId(1L);
        Long carNum = 9L;
        carEntity.setCarStock(carNum);
        carEntity.setCarDailyPrice(200.0);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        Mockito.when(userEntityMapper.selectByPrimaryKey(userEntity.getId())).thenReturn(userEntity);
        Mockito.when(orderEntityMapper.selectUnCompleteByUserId(userEntity.getId())).thenReturn(0);
        Mockito.when(carEntityMapper.selectByPrimaryKey(carEntity.getId())).thenReturn(carEntity);

        orderEntityService.createOrder(orderEntity);

        Assertions.assertEquals(orderEntity.getOrderStatus(), OrderEntity.ORDER_STATUS_UNPAID);
        Assertions.assertEquals(carEntity.getCarStock(), carNum - 1);
        Assertions.assertEquals(orderEntity.getOrderPrice(), total);
    }

    @Test
    void cancelOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderStatus(0);
        orderEntity.setCarId(1L);

        CarEntity carEntity = new CarEntity();
        carEntity.setId(1L);
        Long carNum = 9L;
        carEntity.setCarStock(carNum);

        Mockito.when(orderEntityMapper.selectByPrimaryKey(orderEntity.getId())).thenReturn(orderEntity);
        Mockito.when(orderEntityMapper.updateByPrimaryKeySelective(orderEntity)).thenAnswer((invocationOnMock -> {
            orderEntity.setOrderStatus(2);
            return null;
        }));

        Mockito.when(carEntityMapper.selectByPrimaryKey(carEntity.getId())).thenReturn(carEntity);

        orderEntityService.cancelOrder(orderEntity);

        Assertions.assertEquals(orderEntity.getOrderStatus(), 2);
        Assertions.assertEquals(carEntity.getCarStock(), carNum + 1);
    }

    @Test
    void payOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderStatus(0);

        Mockito.when(orderEntityMapper.selectByPrimaryKey(orderEntity.getId())).thenReturn(orderEntity);
        Mockito.when(orderEntityMapper.updateByPrimaryKeySelective(orderEntity)).thenAnswer((invocationOnMock -> {
            orderEntity.setOrderStatus(1);
            return null;
        }));

        orderEntityService.payOrder(orderEntity);

        Assertions.assertEquals(orderEntity.getOrderStatus(), 1);
    }

    @Test
    public void orderNotExistsExceptionTest() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderStatus(0);

        Mockito.when(orderEntityMapper.selectByPrimaryKey(orderEntity.getId())).thenReturn(null);

        boolean flag = false;
        try {
            orderEntityService.payOrder(orderEntity);

        } catch (ServiceException e) {
            flag = true;
        }

        Assertions.assertEquals(flag, true);

        flag = false;
        try {
            orderEntityService.completeOrder(orderEntity);

        } catch (ServiceException e) {
            flag = true;
        }

        Assertions.assertEquals(flag, true);

        flag = false;
        try {
            orderEntityService.cancelOrder(orderEntity);

        } catch (ServiceException e) {
            flag = true;
        }

        Assertions.assertEquals(flag, true);
    }

    @Test
    void completeOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderStatus(0);
        orderEntity.setCarId(1L);

        CarEntity carEntity = new CarEntity();
        carEntity.setId(1L);
        Long carNum = 9L;
        carEntity.setCarStock(carNum);

        Mockito.when(orderEntityMapper.selectByPrimaryKey(orderEntity.getId())).thenReturn(orderEntity);
        Mockito.when(orderEntityMapper.updateByPrimaryKeySelective(orderEntity)).thenAnswer((invocationOnMock -> {
            orderEntity.setOrderStatus(3);
            return null;
        }));

        Mockito.when(carEntityMapper.selectByPrimaryKey(carEntity.getId())).thenReturn(carEntity);

        orderEntityService.cancelOrder(orderEntity);

        Assertions.assertEquals(orderEntity.getOrderStatus(), 3);
        Assertions.assertEquals(carEntity.getCarStock(), carNum + 1);
    }
}