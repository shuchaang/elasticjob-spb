package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author shuc001
 * @Date 2019-07-20 17:19
 */
@Mapper
public interface OrderMapper {
    List<Order> selectAll(@Param("date") Date date);

    void updateStatus(@Param("id") Integer id);
}
