package com.example.servercode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servercode.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
