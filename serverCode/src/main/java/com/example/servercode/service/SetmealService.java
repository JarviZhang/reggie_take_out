package com.example.servercode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.servercode.dto.SetmealDto;
import com.example.servercode.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    /*
    * 新增套餐并保存套餐和菜品的关系
    * */
    public void saveWithDish(SetmealDto setmealDto);
}
