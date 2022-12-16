package com.example.car.service;

import com.example.car.model.OrderEntity;

import java.util.List;

public interface OrderEntityService {

    void createOrder(OrderEntity orderEntity);

    void cancelOrder(OrderEntity orderEntity);

    void payOrder(OrderEntity orderEntity);

    void completeOrder(OrderEntity orderEntity);

    List<OrderEntity> listOrder(OrderEntity orderEntity, Integer page, Integer pageSize);
}
