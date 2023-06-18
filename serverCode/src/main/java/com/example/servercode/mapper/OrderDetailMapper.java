package com.example.servercode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servercode.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
