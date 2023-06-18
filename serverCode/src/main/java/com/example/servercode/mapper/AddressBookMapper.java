package com.example.servercode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servercode.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
