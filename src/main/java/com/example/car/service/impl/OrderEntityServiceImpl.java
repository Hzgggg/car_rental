package com.example.car.service.impl;

import com.example.car.base.ErrorCodeEnum;
import com.example.car.exception.ServiceException;
import com.example.car.mapper.CarEntityMapper;
import com.example.car.mapper.OrderEntityMapper;
import com.example.car.mapper.UserEntityMapper;
import com.example.car.model.CarEntity;
import com.example.car.model.OrderEntity;
import com.example.car.model.UserEntity;
import com.example.car.service.OrderEntityService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderEntityServiceImpl implements OrderEntityService {

    @Resource
    private OrderEntityMapper orderEntityMapper;

    @Resource
    private CarEntityMapper carEntityMapper;

    @Resource
    private UserEntityMapper userEntityMapper;

    @Override
    public void createOrder(OrderEntity orderEntity) {
        synchronized (CarEntityServiceImpl.class) {
            synchronized (OrderEntityServiceImpl.class) {
                //        Get user info
                UserEntity userEntity = userEntityMapper.selectByPrimaryKey(orderEntity.getUserId());
//        check user exists
                if (userEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_USER_NOT_EXISTS_ERROR);
                }
//        Check if user exists not complete order
                int existsOrderNum = orderEntityMapper.selectUnCompleteByUserId(orderEntity.getUserId());
                if (existsOrderNum > 0) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_COMPLETED_ERROR);
                }
//        Get car daily prices
                CarEntity carEntity = carEntityMapper.selectByPrimaryKey(orderEntity.getCarId());
//        check car exists
                if (carEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_CAR_NOT_EXISTS_ERROR);
                }
//        Check inventory quantity
                if (carEntity.getCarStock() <= 0) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_STOCK_ERROR);
                }
//        Calculate the total price
                long days = orderEntity.getRentalEndTime().getTime() - orderEntity.getRentalStartTime().getTime();
                orderEntity.setOrderPrice((double) (days / (24 * 60 * 60 * 1000)) * carEntity.getCarDailyPrice());
//        Generate random order numbers
                orderEntity.setOrderNum(String.valueOf(UUID.randomUUID()));
                orderEntity.setOrderStatus(OrderEntity.ORDER_STATUS_UNPAID);
//        Insert data into the database
                orderEntityMapper.insertSelective(orderEntity);
//        Update car stock
                carEntity.setCarStock(carEntity.getCarStock() - 1);
                carEntity.setUpdateTime(new Date());
                carEntityMapper.updateByPrimaryKeySelective(carEntity);

            }
        }
    }

    @Override
    public void cancelOrder(OrderEntity orderEntity) {
        synchronized (CarEntityServiceImpl.class) {
            synchronized (OrderEntityServiceImpl.class) {
//        query order info
                orderEntity = orderEntityMapper.selectByPrimaryKey(orderEntity.getId());
                if (orderEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
                }
//        check order current status
                if (orderEntity.getOrderStatus() != OrderEntity.ORDER_STATUS_UNPAID) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_STATUS_ERROR);
                }
//        Modify order status
                orderEntity.setOrderStatus(OrderEntity.ORDER_STATUS_CANCELED);
                orderEntityMapper.updateByPrimaryKeySelective(orderEntity);
//        Release car stock num
                CarEntity carEntity = carEntityMapper.selectByPrimaryKey(orderEntity.getCarId());
                if (carEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_CAR_NOT_EXISTS_ERROR);
                }
                carEntity.setCarStock(carEntity.getCarStock() + 1);
                carEntityMapper.updateByPrimaryKeySelective(carEntity);
            }
        }
    }

    @Override
    public void payOrder(OrderEntity orderEntity) {
        synchronized (OrderEntityServiceImpl.class) {
            //        query order info
            orderEntity = orderEntityMapper.selectByPrimaryKey(orderEntity.getId());
            if (orderEntity == null) {
                throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
            }
//        check order current status
            if (orderEntity.getOrderStatus() != OrderEntity.ORDER_STATUS_UNPAID) {
                throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_STATUS_ERROR);
            }
//        modify order status
            orderEntity.setOrderStatus(OrderEntity.ORDER_STATUS_PAID);
            orderEntityMapper.updateByPrimaryKeySelective(orderEntity);
        }
    }

    @Override
    public void completeOrder(OrderEntity orderEntity) {
        synchronized (CarEntityServiceImpl.class) {
            synchronized (OrderEntityServiceImpl.class) {
//        query order info
                orderEntity = orderEntityMapper.selectByPrimaryKey(orderEntity.getId());
                if (orderEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_NOT_EXISTS_ERROR);
                }
//        check order current status
                if (orderEntity.getOrderStatus() != OrderEntity.ORDER_STATUS_PAID) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_ORDER_STATUS_ERROR);
                }
//        Modify order status
                orderEntity.setOrderStatus(OrderEntity.ORDER_STATUS_CANCELED);
                orderEntityMapper.updateByPrimaryKeySelective(orderEntity);
//        Release car stock num
                CarEntity carEntity = carEntityMapper.selectByPrimaryKey(orderEntity.getCarId());
                if (carEntity == null) {
                    throw new ServiceException(ErrorCodeEnum.SERVICE_CAR_NOT_EXISTS_ERROR);
                }
                carEntity.setCarStock(carEntity.getCarStock() + 1);
                carEntityMapper.updateByPrimaryKeySelective(carEntity);
            }
        }
    }

    @Override
    public List<OrderEntity> listOrder(OrderEntity orderEntity, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return orderEntityMapper.selectUnCompleteKeySelective(orderEntity);
    }

}
