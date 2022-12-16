package com.example.car.model;

import lombok.Data;

import java.util.Date;

/**
 * car info tab
 */
@Data
public class CarEntity {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String carModel;

    /**
     *
     */
    private Long carStock;

    /**
     *
     */
    private Double carDailyPrice;

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