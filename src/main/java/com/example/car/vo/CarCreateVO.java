package com.example.car.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Accessors(chain = true)
public class CarCreateVO {
    /**
     *
     */
    @NotBlank
    private String carModel;

    /**
     *
     */
    @Positive
    private Long carStock;

    /**
     *
     */
    @DecimalMin(value = "0")
    private Double carDailyPrice;

}