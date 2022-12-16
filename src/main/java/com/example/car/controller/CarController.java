package com.example.car.controller;

import com.example.car.base.ResponseResult;
import com.example.car.model.CarEntity;
import com.example.car.service.CarEntityService;
import com.example.car.vo.CarCreateVO;
import com.example.car.vo.CarDeleteVO;
import com.example.car.vo.CarListVO;
import com.example.car.vo.CarUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarEntityService carEntityService;

    @PostMapping("/create")
    public ResponseResult create(@RequestBody @Validated CarCreateVO data) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        carEntityService.create(carEntity);
        return new ResponseResult();
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody @Validated CarDeleteVO data) {
        carEntityService.delete(data.getId());
        return new ResponseResult();
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody @Validated CarUpdateVO data) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        carEntityService.update(carEntity);
        return new ResponseResult();
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody @Validated CarListVO data) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        List<CarEntity> carEntities = carEntityService.list(carEntity, data.getPage(), data.getPageSize());
        ResponseResult result = new ResponseResult();
        result.setData(carEntities);
        return result;
    }

}
