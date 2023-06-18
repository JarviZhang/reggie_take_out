package com.example.servercode.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 用户信息
 * </p>
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private String avatar;

    private Integer status;

}
