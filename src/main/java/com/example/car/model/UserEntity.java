package com.example.car.model;

import lombok.Data;

import java.util.Date;

/**
 * car info tab
 */
@Data
public class UserEntity {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String userName;

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