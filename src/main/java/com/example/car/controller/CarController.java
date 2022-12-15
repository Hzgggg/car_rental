package com.example.car.controller;

import com.example.car.base.ResponseResult;
import com.example.car.model.CarEntity;
import com.example.car.service.CarEntityService;
import com.example.car.vo.CarCreateVo;
import com.example.car.vo.CarDeleteVo;
import com.example.car.vo.CarListVo;
import com.example.car.vo.CarUpdateVo;
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
    public ResponseResult create(@RequestBody @Validated CarCreateVo data) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        carEntityService.insertSelective(carEntity);
        return new ResponseResult();
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody @Validated CarDeleteVo data) {
        carEntityService.deleteByPrimaryKey(data.getId());
        return new ResponseResult();
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody @Validated CarUpdateVo data) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        carEntityService.updateByPrimaryKeySelective(carEntity);
        return new ResponseResult();
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody @Validated CarListVo data) {
        // 使用SpringValidation校验数据
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(data, carEntity);
        List<CarEntity> carEntities = carEntityService.selectCarList(carEntity);
        ResponseResult result = new ResponseResult();
        result.setData(carEntities);
        return result;
    }

}
