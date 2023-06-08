package com.example.servercode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.servercode.common.CustomException;
import com.example.servercode.entity.Category;
import com.example.servercode.entity.Dish;
import com.example.servercode.entity.Setmeal;
import com.example.servercode.mapper.CategoryMapper;
import com.example.servercode.service.CategoryService;
import com.example.servercode.service.DishService;
import com.example.servercode.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /*
    * 根据id删除分类
    * */
    @Override
    public void remove(Long id) {
        //查询分类是否关联了菜品，如果已关联则抛出业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(dishLambdaQueryWrapper);
        if (count > 0){
            //已关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下已经关联菜品，不能删除");
        }
        //查询分类是否关联了套餐，如果已关联则抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0){
            //已关联套餐，抛出一个异常
            throw new CustomException("当前分类下已经关联套餐，不能删除");
        }
        //正常删除分类
        super.removeById(id);
    }
}
