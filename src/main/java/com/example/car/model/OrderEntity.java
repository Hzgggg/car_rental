package com.example.car.model;

import lombok.Data;

import java.util.Date;

/**
 * car info tab
 */
@Data
public class OrderEntity {
    //    0-unpaid, 1-paid, 2-canceled, 3-completed
    public final static int ORDER_STATUS_UNPAID = 0;
    public final static int ORDER_STATUS_PAID = 1;
    public final static int ORDER_STATUS_CANCELED = 2;
    public final static int ORDER_STATUS_COMPLETED = 3;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String orderNum;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long carId;

    /**
     *
     */
    private Date rentalStartTime;

    /**
     *
     */
    private Date rentalEndTime;

    /**
     *
     */
    private Double orderPrice;

    /**
     * 0-unpaid, 1-paid, 2-canceled, 3-completed
     */
    private Integer orderStatus;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer deleteFlag;
}