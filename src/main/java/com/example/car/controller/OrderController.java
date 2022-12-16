package com.example.car.controller;

import com.example.car.base.ErrorCodeEnum;
import com.example.car.base.ResponseResult;
import com.example.car.exception.ServiceException;
import com.example.car.model.OrderEntity;
import com.example.car.service.OrderEntityService;
import com.example.car.vo.OrderCreateVO;
import com.example.car.vo.OrderListVO;
import com.example.car.vo.OrderStatusUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderEntityService orderEntityService;

    @PostMapping("/create")
    public ResponseResult orderCreate(@RequestBody @Validated OrderCreateVO data) {
//        check rental date
        if (data.getRentalStartTime().after(data.getRentalEndTime())) {
            throw new ServiceException(ErrorCodeEnum.PARAMETER_ERROR);
        }
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(data, orderEntity);
        orderEntityService.createOrder(orderEntity);
        return new ResponseResult();
    }

    @PostMapping("/cancel")
    public ResponseResult orderCancel(@RequestBody @Validated OrderStatusUpdateVO data) {
        if (data.getId() == null && (data.getOrderNum() == null || data.getOrderNum().equals(""))) {
            throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
        }
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(data, orderEntity);
        orderEntityService.cancelOrder(orderEntity);
        return new ResponseResult();
    }

    @PostMapping("/pay")
    public ResponseResult orderPay(@RequestBody @Validated OrderStatusUpdateVO data) {
        if (data.getId() == null && (data.getOrderNum() == null || data.getOrderNum().equals(""))) {
            throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
        }
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(data, orderEntity);
        orderEntityService.payOrder(orderEntity);
        return new ResponseResult();
    }

    @PostMapping("/complete")
    public ResponseResult orderComplete(@RequestBody @Validated OrderStatusUpdateVO data) {
        if (data.getId() == null && (data.getOrderNum() == null || data.getOrderNum().equals(""))) {
            throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
        }
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(data, orderEntity);
        orderEntityService.completeOrder(orderEntity);
        return new ResponseResult();
    }

    @PostMapping("/list")
    public ResponseResult orderList(@RequestBody @Validated OrderListVO data) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(data, orderEntity);
        List<OrderEntity> orderEntities = orderEntityService.listOrder(orderEntity, data.getPage(), data.getPageSize());
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(orderEntities);
        return responseResult;
    }

}
