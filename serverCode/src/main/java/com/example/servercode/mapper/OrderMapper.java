package com.example.servercode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servercode.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
