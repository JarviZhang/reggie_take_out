package com.example.servercode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.servercode.dto.SetmealDto;
import com.example.servercode.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /*
    * 新增套餐并保存套餐和菜品的关系
    * */
    public void saveWithDish(SetmealDto setmealDto);

    /*
    * 删除套餐,同时删除套餐的关联数据
    * */
    public void removeWithDish(List<Long> ids);
}
