package com.example.servercode.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String image;

    private Long orderId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;


}
