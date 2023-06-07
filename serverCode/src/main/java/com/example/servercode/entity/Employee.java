package com.example.servercode.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;

/*
* 员工实体类
* */

//Lombok 注解
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private String sex;
    //身份证号码
    private String idNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


}
