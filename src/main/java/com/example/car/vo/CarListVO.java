package com.example.car.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Accessors(chain = true)
public class CarListVO {

    private String carModel;

    @PositiveOrZero
    private Long carStock;

    @NotNull
    @PositiveOrZero
    private Integer page;

    @NotNull
    @Positive
    private Integer pageSize;
}