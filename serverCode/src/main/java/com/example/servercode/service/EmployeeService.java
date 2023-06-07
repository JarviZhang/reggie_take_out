package com.example.servercode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.servercode.entity.Employee;

//IService 类是 MyBatis-Plus 框架提供的一个接口，用于定义通用的服务层接口。它提供了一些常用的数据库操作方法
public interface EmployeeService extends IService<Employee> {
}
