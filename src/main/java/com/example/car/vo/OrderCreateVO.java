package com.example.car.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderCreateVO {
    /**
     *
     */
    @NotNull
    private Long userId;

    /**
     *
     */
    @NotNull
    private Long carId;

    /**
     *
     */
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date rentalStartTime;

    /**
     *
     */
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentalEndTime;
}