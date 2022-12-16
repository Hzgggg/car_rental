package com.example.car.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@Accessors(chain = true)
public class CarDeleteVO {
    /**
     *
     */
    @NotNull
    @Positive
    private Long id;
}