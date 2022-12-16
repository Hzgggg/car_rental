package com.example.car.mapper;

import com.example.car.model.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderEntityMapper {
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
    int insert(OrderEntity record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(OrderEntity record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OrderEntity selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OrderEntity record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderEntity record);

    /**
     * uncomplete order
     *
     * @param record the updated record
     * @return update count
     */
    int selectUnCompleteByUserId(Long userId);

    /**
     * uncomplete order
     *
     * @param record the updated record
     * @return update count
     */
    List<OrderEntity> selectUnCompleteKeySelective(OrderEntity orderEntity);
}