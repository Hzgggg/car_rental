package com.example.car.mapper;

import com.example.car.model.CarEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarEntityMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(CarEntity record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(CarEntity record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    CarEntity selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(CarEntity record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(CarEntity record);

    /**
     * select CarList
     *
     * @param record the select condition
     * @return carEntity list
     */
    List<CarEntity> selectCarList(CarEntity carEntity);
}