package com.example.car.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
public class OrderListVO {
    private Long userId;

    private Long carId;

    private Integer orderStatus;

    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentalStartTime;

    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentalEndTime;

    @NotNull
    @Positive
    private Integer page;

    @NotNull
    @PositiveOrZero
    private Integer pageSize;
}