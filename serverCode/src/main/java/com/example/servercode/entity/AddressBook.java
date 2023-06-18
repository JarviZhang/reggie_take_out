package com.example.servercode.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 地址管理
 * </p>
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;

    private String consignee;
    private Integer sex;

    private String phone;

    private String provinceCode;

    private String provinceName;

    private String cityCode;
    private String cityName;
    private String districtCode;

    private String districtName;
    private String detail;

    private String label;

    private Boolean isDefault;

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
