package com.example.car.model;

import lombok.Data;

import java.util.Date;

/**
 * car info tab
 */
@Data
public class OrderEntity {
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
    private Byte orderStatus;

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
    private Byte deleteFlag;
}