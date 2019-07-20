package com.example.mapper;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shuc001
 * @Date 2019-07-20 17:20
 */
@Data
public class Order {
    private Integer id;
    private BigDecimal amount;
    private Integer status;
    private String receiveName;
    private String receiveAddress;
    private Date createTime;
}
