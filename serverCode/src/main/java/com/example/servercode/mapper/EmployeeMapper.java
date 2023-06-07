package com.example.servercode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servercode.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
