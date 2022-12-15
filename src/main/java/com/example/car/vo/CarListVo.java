package com.example.car.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.PositiveOrZero;

@Data
@Accessors(chain = true)
public class CarListVo {

    private String carModel;

    @PositiveOrZero
    private Long carStock;
}