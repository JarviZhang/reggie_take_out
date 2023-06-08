package com.example.servercode.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author anyi
 * @since 2022-05-24
 */
@Data
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    private Integer status;

    private String code;

    private String description;

    private String image;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    private Integer isDeleted;


}
